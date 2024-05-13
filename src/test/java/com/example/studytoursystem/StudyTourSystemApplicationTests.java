package com.example.studytoursystem;

import static org.junit.Assert.*;

import com.example.studytoursystem.model.Location;
import com.example.studytoursystem.model.LocationQuery;
import com.example.studytoursystem.service.impl.LocationServiceImpl;
import com.example.studytoursystem.utils.HeapSort;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class StudyTourSystemApplicationTests {

    @Test
    void testBoyerMooreChinese() {
        LocationServiceImpl locationService = new LocationServiceImpl();
        LocationQuery locationQuery = new LocationQuery("北京", 1, "邮电",0);
        HeapSort heapSort = new HeapSort();
        List<Location> locations = new ArrayList<>();

    }

}
