package com.example.studytoursystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studytoursystem.mapper.LocationMapper;
import com.example.studytoursystem.model.Location;
import com.example.studytoursystem.model.LocationQuery;
import com.example.studytoursystem.service.LocationService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.studytoursystem.utils.HeapSort;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationMapper locationMapper;

    @Override
    public List<Location> getLocation() {

        return locationMapper.getAllLocation();
    }

    @Override
    public List<Location> getQueryLocation(LocationQuery query){
        List<Location> allLocation = locationMapper.getAllLocation();
        List<Location> res = locationMapper.getAllLocation();
        if (query.getName() != null) {
            for(Location location : allLocation){
                if(!boyerMooreChinese(location.getName(), query.getName())){
                    res.remove(location);
                }
            }
        }
        if (query.getKeyword() != null) {
            for(Location location : res){
                if(location.getKeyword() != query.getKeyword()){
                    res.remove(location);
                }
            }
        }
        if(query.getType() != null){
            for(Location location : res){
                if(location.getType() != query.getType()){
                    res.remove(location);
                }
            }
        }
        if(query.getSortOrder() != null){
            HeapSort<Location> heapSort = new HeapSort<>();
            if(query.getSortOrder() == 1){
                heapSort.sort(res, (o1, o2) -> o2.getPopularity() - o1.getPopularity());
            }else{
                heapSort.sort(res, (o1, o2) -> o2.getEvaluation() - o1.getEvaluation());
            }
        }

        if(res == allLocation){
            return getLocation();
        }
        else {
            List<Location> res2 = new ArrayList<>();
            for(int i = 0; i < (res.size() > 10 ? 10 : res.size()); i++){
                res2.add(res.get(i));
            }
            return res2;
        }
    }
    private boolean boyerMooreChinese(String text, String pattern) {
        int patternLength = pattern.length();
        int textLength = text.length();

        if (patternLength > textLength) {
            return false;
        }

        // 初始化坏字符表，考虑到Unicode字符，这里使用HashMap而非固定大小的数组
        Map<Character, Integer> badCharShift = new HashMap<>();
        for (char c : pattern.toCharArray()) {
            badCharShift.put(c, patternLength);
        }
        for (int i = 0; i < patternLength; i++) {
            badCharShift.put(pattern.charAt(i), patternLength - i - 1);
        }

        int[] goodSuffixShift = new int[patternLength + 1];
        computeGoodSuffixShifts(pattern, goodSuffixShift);

        int skip = 0;
        for (int i = patternLength - 1; i < textLength; ) {
            if (pattern.charAt(patternLength - 1) == text.charAt(i)) {
                boolean matched = true;
                for (int j = patternLength - 1, k = i; j >= 0 && matched; j--, k--) {
                    if (pattern.charAt(j) != text.charAt(k)) {
                        matched = false;
                        int suffixIndex = j;
                        skip = goodSuffixShift[suffixIndex];
                        if (skip == 0) {
                            skip = badCharShift.getOrDefault(text.charAt(k), patternLength);
                        }
                    }
                }
                if (matched) {
                    return true;
                }
            }
            i += skip;
        }

        return false;
    }

    private void computeGoodSuffixShifts(String pattern, int[] goodSuffixShift) {
        int patternLength = pattern.length();
        goodSuffixShift[patternLength] = 0;

        // 遍历模式字符串的后缀，从最长后缀开始
        for (int i = patternLength - 1; i >= 0; i--) {
            // 查找与模式前缀相同的最长后缀
            int j = i;
            while (j < patternLength && pattern.charAt(j) == pattern.charAt(patternLength - 1 - j)) {
                j++;
            }

            // 如果找到了一个完全匹配的后缀，计算它在模式中的位置
            if (j > i) {
                int suffixLength = j - i;
                goodSuffixShift[i] = patternLength - suffixLength;
            } else {
                // 如果没有找到匹配的后缀，设置为0
                goodSuffixShift[i] = 0;
            }
        }
    }
}

