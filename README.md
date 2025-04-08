# 2024 BUPT北邮计院 数据结构课程设计 🎓📚

本课设最后获得了100分的成绩🏆

如果你觉得本项目对你有帮助，请多多star✨ thx！

## 一、项目介绍 🎉

学生游学系统是一款旨在帮助学生管理和优化自己游学活动的应用程序 🌍✈️。这个系统为学生提供了全面的功能，包括游学目的地推荐、路线规划、场所查询和游学日记管理等，以确保他们能够充分利用假期进行有意义的学习和探索。

### 1.1 项目背景 🏫🌏

随着教育水平的提高和社会发展的进步，学生游学成为一种广泛受到欢迎的学习方式。游学不仅能够开拓学生的视野，增强其对文化、历史和地理的认识，还可以促进学生的个人成长和发展。然而，学生在进行游学活动时面临着诸多挑战，包括目的地选择、路线规划、信息查询和经验总结等问题。为了解决这些问题，提高学生游学活动的效率和质量，我们开发了学生游学系统。

### 1.2 项目将采用的技术 🖥️💻

项目采用前后端分离的设计模式、浏览器 / 服务器（B/S）的架构模式。后端（服务端）项目主体主要利用Java负责全部计算任务；前端（客户端）仅承担信息展示与交互的任务，不参与任何计算。

## 二、项目目标 🎯

### 2.0 用户信息储存 💾

利用哈希表储存用户的信息以便快速查询，我们将使用拉链法重写哈希表，实现自己哈希表的 `put`、`remove`、`get`、`getOrDefault` 以及扩容算法。

### 2.1 游学目的地推荐 🗺️

对于新用户基于内容推荐，对于评分以及浏览较多的用户使用协同过滤推荐等，进行景点、学校和游学日记的推荐。构建一个能够根据学生的个人兴趣和偏好，智能地推荐适合他们的游学目的地的系统。

### 2.2 路线规划 🚶‍♂️🚗

在游学过程中，学生可以利用系统基于数据结构的算法，规划最优的参观路线。系统主要使用 Dijkstra 算法和广度优先搜索算法，考虑了游览目标、时间和距离等因素，通过数据结构的图论算法为用户提供最佳的游览路线。

### 2.3 场所查询 🏰

在景区或者学校内部时，学生可以根据自己的需求，选择一个目标景点或者场所信息或多个目标景点或者场所信息。采用 BFS 的想法，对于用户输入的搜索距离和搜索目标寻找景点或场所。

### 2.4 图形界面设计 💻🎨

使用 Vue 框架设计一个用户友好、直观的图形页面界面，使学生能够轻松、快捷地使用学生游学系统的各项功能。确保页面设计符合现代化美学标准，提升用户体验。

### 2.5 游学日记管理 📖📸

游学结束后，学生可以利用系统管理和整理所拍照片和游览经历，生成游学日记。系统使用快速排序算法对游学日记根据热度和评分进行排序。使用哈希表或二分查找算法，根据输入的游学日记名称在日记列表中进行精准查询 📑💡。

### 2.6 交通工具的最短时间策略 🚄🚴‍♀️

交通工具的最短时间，对于每一次遍历我们采用 Dijkstra 算法。在每一次 Dijkstra 以便学生在规划游学路线时能够快速找到最优的交通方式。

## 问题 ❓
本人技术水平很烂，可能有些小bug。
如果有疑问，或者项目有 bug，可以联系我 📧

Email: bhr1234sr@163.com

另，如果对于前端代码有疑问，可以询问另一位contributer [z6iyu](https://github.com/z6iyu)
## 项目结构 📂

**frontend** 存储了前端代码

- 📁 main
  - 📁 java
    - 📁 com
      - 📁 example
        - 📁 studytoursystem
          - ☕ `StudyTourSystemApplication.java` // 启动文件
          - 📁 controller
            - ☕ `ArticleController.java`
            - ☕ `ArticleScoreController.java`
            - ☕ `LocationBrowseController.java`
            - ☕ `LocationController.java`
            - ☕ `PathController.java`
            - ☕ `SpotController.java`
            - ☕ `UserController.java`
            - ☕ `VertexController.java`
          - 📁 mapper
            - ☕ `ArticleMapper.java`
            - ☕ `ArticleScoreMapper.java`
            - ☕ `LocationBrowseCountMapper.java`
            - ☕ `LocationMapper.java`
            - ☕ `PathMapper.java`
            - ☕ `UserMapper.java`
            - ☕ `VertexMapper.java`
          - 📁 model
            - ☕ `Article.java`
            - ☕ `ArticleScore.java`
            - ☕ `Edge.java`
            - ☕ `EdgeNode.java`
            - ☕ `Location.java`
            - ☕ `LocationBrowseCount.java`
            - ☕ `LocationBrowsePlus.java`
            - ☕ `LocationQuery.java`
            - ☕ `NewArticleAdd.java`
            - ☕ `PathQuery.java`
            - ☕ `Result.java`
            - ☕ `SimplifiedArticle.java`
            - ☕ `Spot.java`
            - ☕ `SpotQuery.java`
            - ☕ `User.java`
            - ☕ `Vertex.java`
            - 📁 graph
              - ☕ `Edge.java`
              - ☕ `Graph.java`
              - ☕ `Node.java`
          - 📁 service
            - ☕ `ArticleScoreService.java`
            - ☕ `ArticleService.java`
            - ☕ `LocationBrowseCountService.java`
            - ☕ `LocationService.java`
            - ☕ `PathService.java`
            - ☕ `SpotService.java`
            - ☕ `UserService.java`
            - ☕ `VertexService.java`
            - 📁 impl
              - ☕ `ArticleScoreServicelmpl.java`
              - ☕ `ArticleServicelmpl.java`
              - ☕ `LocationBrowseCountServicelmpl.java`
              - ☕ `LocationServiceImpl.java`
              - ☕ `PathServiceImpl.java`
              - ☕ `SpotServiceImpl.java`
              - ☕ `UserServiceImpl.java`
              - ☕ `VertexSeriveImpl.java`
          - 📁 utils
            - ☕ `BoyerMooreChinese.java`
            - ☕ `CustomHashMap.java`
            - ☕ `Dijkstra.java`
            - ☕ `HeapSort.java`
            - ☕ `HeldKarp.java`
            - ☕ `HuffmanCompression.java`
            - ☕ `HuffmanDecompression.java`
            - ☕ `HuffmanNode.java`
            - ☕ `InsertSort.java`
            - ☕ `LocationBasedRecommend.java`
            - ☕ `MyHashMap.java`
            - ☕ `MyPriorityQueue.java`
            - ☕ `SimulatedAnnealing.java`
            - ☕ `UserBasedCF.java`
- 📁 resources
  - 📄 `application.properties`
  - 📁 database
    - 🗎 `study_tour_system.sql`
- 📁 test

## 注意学术诚信 📜

本项目严格遵守学术诚信原则，所有代码和文档内容均为原创或已标明引用来源。未经授权，禁止直接复制、抄袭或转让本项目内容。希望大家在学习中遵循诚实守信的原则，共同维护良好的学术环境 📚💡。
