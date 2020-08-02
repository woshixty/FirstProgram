# API(不用分页返回数据)

静态资源: 
文本  http://localhost/image/1.jpg
图片  http://localhost/txt/1.txt

项目URL  http://localhost:8080/bookproject/.../...

# 1.书城部分

### 获取所有书籍简略信息

```
GET  http://localhost:8080/bookproject/partOne/getAllBook
```

参数

```
无
```

返回

```json

{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "book_id": "12345678",
            "book_name": "Java核心技术",
            "book_cover": "http://xxx.com",
        },
        {
            "book_id": "12345678",
            "book_name": "C++ Primer Plus",
            "book_cover": "http://xxx.com",
        }
        ...
    ]
}
```

### 获取一本书籍详细信息 

```
GET http://localhost:8080/bookproject/partOne/getOneBookInfo
```

参数

```
book_id: "12345678"
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "book_id": "12345678",
            "book_name": "Java核心技术",
            "book_cover": "http://xxx.com",
            "book_intro": "程序员宝典",
            "tag_name": "IT",
            "book_author": "霍斯特曼"
            "book_num_collect": 150,
            "book_num_command": 100,
        }
    ]
}
########################################################
{
  "code": 1,
  "msg": "找不到本书",
  "data": null
}

```

### 获取一本书的评论详情

```
GET  .../partOne/getOneBookCommands
```

参数

```
book_id: "12345678"
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "book_id": "12345678"
            "book_name": "Java核心技术",
            "commands": [
                {
                    "user_id": "11223344",
                    "user_name": "星天熊"，
                    "command_context": "我TM爱死这本书了！！！",
                    "command_time": 2020-07-06,
                    "command_like": 250,
                },
                {
                    "user_id": "11223355",
                    "user_name": "Mr.Stark"，
                    "command_context": "我TM也爱死这本书了！！！",
                    "command_time": 2020-07-07,
                    "command_like": 150,
                }
                ...
            ]
        }
    ]
}
```

###  收藏一本书 *(用户已收藏之后不能再收藏,后台做判断)*

```
POST  .../partOne/collectOneBook
```

参数

```
book_id: "12345678"
user_id: "11223344"
```

返回

```
(该用户未收藏该书的情况)
{
  "code": 0,
  "msg": "成功",
  "data": [
      {
          "book_num_collect": 5;
      }
  ]
}
########################################################
(若该用户已收藏,返回1,返回data为null)
{
  "code": 1,
  "msg": "该用户已收藏本书",
  "data": null
}

```

### 获取书籍类目列表

```
GET   .../partOne/getAllTagInfo
```

参数

```
无
```

返回

```
{
  "code": 0,
  "msg": "成功",
  "data": [
    {
      "tag_id": 1,
      "tag_name": "IT",
      "tag_photo": "http://xxx.com"
    },
    {
      "tag_id": 2,
      "tag_name": "玄幻",
      "tag_photo": "http://xxx.com"
    },
    {
      "tag_id": 3,
      "tag_name": "文学",
      "tag_photo": "http://xxx.com"
    },
    ...
    ]
}
```

### 获取一种类别的全部书籍

```
GET    .../partOne/getBooksByTag
```

参数

```
tag_id: 1
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "book_id": "12345678",
            "book_name": "Java核心技术",
            "book_cover": "http://xxx.com",
        },
        {
            "book_id": "12345678",
            "book_name": "C++ Primer Plus",
            "book_cover": "http://xxx.com",
        }
        ...
    ]
```

### 获取收藏量前10书籍(书籍数量不足10本不足就全部返回)

```
GET    .../partOne/getTop10Books
```

参数

```
无
```

返回

```
收藏量前10的书籍

{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "book_id": "12345678",
            "book_name": "Java核心技术",
            "book_cover": "http://xxx.com",
            "book_intro": "程序员宝典",
            "tag_name": "IT",
            "book_num_collect": 150,
            "book_num_command": 100,
        },
        {
            ""book_id": "87654321",
            "book_name": "C++ Primer Plus",
            "book_cover": "http://xxx.com",
            "book_intro": "C++入门宝典",
            "tag_name": "IT",
            "book_num_collect": 110,
            "book_num_command": 50,
        }
        ...
    ]
}
```

### 发表书籍评论

```
POST   .../partOne/saveOneBookCommand
```

参数

```
book_id: 12345678
user_id: 12345678
command_context: "我TM爱死这本书了！！"
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "book_id": "12345678"，
            "user_id": "11223344",               
            "user_name": "星天熊"，
            "command_context": "我TM爱死这本书了！！！",
            "command_time": 2020-07-06,
            "command_like": 0,              
        }
    ]
}
```



### 书籍加入书架  (这个接口之前忘记写了)***

```
POST   .../partOne/addBookToBookShelf
```

参数

```
book_id: 12345678
user_id: 12345678
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": null
}
########################################################
{
    "code": 1,
    "msg": "书籍已经存在于书架",
    "data": null
}
```

# 2.书架部分

### 获取用户书架上的书籍

```
GET    .../partTwo/getUserBooks
```

参数

```
user_id :"12345678"
```

返回

```

{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "book_id": "12345678",
            "book_name": "Java核心技术",
            "book_cover": "http://xxx.com",
        },
        {
            "book_id": "12345678",
            "book_name": "C++ Primer Plus",
            "book_cover": "http://xxx.com",
        }
        ...
    ]
}
```

### 获取一本书籍详细信息

```
GET   .../partTwo/getOneBookInfo
```

参数

```
book_id: "12345678"
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "book_id": "12345678",
            "book_name": "Java核心技术",
            "book_cover": "http://xxx.com",
            "book_intro": "程序员宝典",
            "tag_name": "IT",
            "book_url": "http://xxx.com",
        }
    ]
}
```

### 上传目录信息(阅读记录储存)

```
POST   .../partTwo/uploadRecords
```

参数

```
book_id: "12345678"
user_id: "12345678"
memory: 3
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": null,
}
########################################################
{
    "code": 1,
    "msg": "未找到书籍",
    "data": null
}
```

### 获取目录信息(拿取阅读记录)

```
GET   .../partTwo/getRecords
```

参数

```
book_id: "12345678"
user_id: "12345678"
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "book_id": "12345678",
            "book_name": "程序员的自我修养"，
            "book_url": "http://xxx.com",
            "memory": 3,
        }
    ]
}

########################################################
{
    "code": 1,
    "msg": "未找到书籍",
    "data": null
}
```

# 3.社区部分(帖子不再与图书相关联)]

### 获取一条帖子的内容

```
GET    .../partThree/getOnePostInfo
```

参数

```
post_id: 11111111
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "post_id": "11111111"，
            "user_id": "12345678",
            "user_name": "星天熊",
            "book_name": "Java核心技术",
            "post_title": "我和Java的爱情故事",
            "post_content": "那一年，我对Java一见钟情，擦出了爱情的花火",
            "post_like": 500,
            "post_tag": "#爱情故事#",
            "post_command_sum": 199,
            "post_time": 2019-11-32,
            "commands": [
                {
                    "command_id": "123456"
                    "user_name": "周旭刚"
                    "command_context": "泪目" 
                    "command_like": 120
                    "command_time": 2019-11-32,
                },
                {
                    "command_id": "123457"
                    "user_name": "方俊雄"
                    "command_context": "类目" 
                    "command_like": 199
                    "command_time": 2019-11-32,
                },
                ...
            ]
        }
    ]
}
#########################################################
{
    "code": 1,
    "msg": "帖子不存在",
    "data": null
}
```

### 获取全部帖子

```
GET    .../partThree/getAllPost
```

参数

```
无
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "post_id": "11111111"，
            "user_id": "12345678",
            *****************************
            "book_name": "java", ---(少加的)
            *****************************
            "user_name": "星天熊",
            "post_title": "我和Java的爱情故事",
            "post_content": "那一年，我对Java一见钟情，擦出了爱情的花火",
            "post_like": 500,
            "post_tag": "#爱情故事#",
            "post_command_sum": 199,
            "post_time": 2019-11-32,
        },
        {
            "post_id": "11111112"，
            "user_id": "12345679",
            *****************************
            "book_name": "java",  ---(少加的)
            *****************************
            "user_name": "周旭刚",
            "post_title": "我和C++的爱情故事",
            "post_content": "那一年，我对C++一见钟情，擦出了爱情的花火",
            "post_like": 450,
            "post_tag": "#爱情故事#",
            "post_command_sum": 299,
            "post_time": 2019-11-32,
        },
        ...
    ]
}
```

### 发帖

```
POST     .../partThree/saveOnePost
```

参数

```
book_name: "Java核心技术"
user_id: "12345678"
post_title: "我和Python的爱情故事"
post_content: "那一年，我对Python一见钟情，擦出了爱情的花火"
post_tag: "#爱情故事#"
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": null,
}

```

### 给帖子点赞

```
POST    .../partThree/likeOnePost
```

参数

```
post_id: "12345678"
user_id: "12345678"
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "post_like": 12
        }
    ]
}
#################################################
{
    "code": 1,
    "msg": "您已经为该帖子点赞",
    "data": null
}
#################################################
{
    "code": 2,
    "msg": "未查找到该帖子",
    "data": null
}
```

### 在帖子下发表评论

```
POST      .../partThree/savePostCommand
```

参数

```
post_id: "12345678"
user_id: "12345678"
command_context: "帖子写的正好!!!"

```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": null
}
#################################################
{
    "code": 1,
    "msg": "未查找到该帖子",
    "data": null
}
```

### 获取发帖记录

```
GET     .../partThree/getPostRecord
```

参数

```
user_id: "12345678"
```

返回

```

{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "post_id": "12345678",
            "post_title": "我和Python的爱情故事",
            "post_like": 12,
            "post_tag": "#爱情故事#",
            "post_time": 1982-09-09,
        },
        {
            "post_id": "12345678",
            "post_title": "我和C++的爱情故事",
            "post_like": 12,
            "post_tag": "#爱情故事#",
            "post_time": 1982-09-10,
        }
        ...
    ]
}
```

### 获取点赞记录(暂时只实现帖子点赞记录)

```
GET    .../partThree/getPostLikeRecord
```

参数

```
user_id: "12345678"
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "post_id": "12345678",
            "post_title": "我和Python的爱情故事",
            "post_like": 12,
            "post_tag": "#爱情故事#",
            "post_time": 1982-09-09,
        },
        {
            "post_id": "12345678",
            "post_title": "我和C++的爱情故事",
            "post_like": 12,
            "post_tag": "#爱情故事#",
            "post_time": 1982-09-10,
        }
        ...
    ]
}
```

### 获取评论记录

```
GET     .../partThree/getPostCommandRecord
```

参数

```
user_id: "12345678"
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "post_id": "12345678",
            "post_title": "我和Python的爱情故事",
            "post_like": 12,
            "post_tag": "#爱情故事#",
            "post_time": 1982-09-09,
            "command_context": "帖子写的正好!!!"
        },
        {
            "post_id": "12345678",
            "post_title": "我和C++的爱情故事",
            "post_like": 12,
            "post_tag": "#爱情故事#",
            "post_time": 1982-09-10,
            "command_context": "帖子写的正好!!!"
        }
        ...
    ]
}
```

### 获取用户收藏的书籍

```
GET     .../partThree/getUserCollectBooks
```

参数
```
user_id :"12345678"
```
返回

```

{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "book_id": "12345678",
            "book_name": "Java核心技术",
            "book_cover": "http://xxx.com",
        },
        {
            "book_id": "12345678",
            "book_name": "C++ Primer Plus",
            "book_cover": "http://xxx.com",
        }
        ...
    ]
}
```

# 4.用户部分

### (用户头像是预留功能,先不管)
### 用户注册

```
POST    .../partFour/register
```

参数

```
(不能为空)
user_id: "12345678"
user_password: "123456"
user_name: "星天熊"
user_personal_word: "我最帅我最帅我最帅!!!"
user_photo: "http://xxx.com"
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": null
}
########################################################
(账户已存在)***
{
    "code": 1,
    "msg": "账户已存在",
    "data": null
}
```

### 用户登录

```
GET     .../partFour/verify
```

参数

```
user_id: "12345678"
user_password: "123456"
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": null
}
########################################################
(账户不存在或者密码错误)
{
    "code": 1,
    "msg": "账户不存在或者密码错误",
    "data": null
}
```

### 用户完善信息(与注册相同)

```
POST    .../partFour/completeInfo
```

参数

```
user_id: "12345678"
user_password: "123456"
user_name: "星天熊"
user_personal_word: "我最帅我最帅我最帅!!!"
user_photo: "http://xxx.com"
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```

### 获取用户基本信息

```
GET    .../partFour/getUserInfo
```

参数

```
user_id: "12345678"
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "user_id": "12345678"
            "user_name": "星天熊"
            "user_personal_word": "我最帅我最帅我最帅!!!"
            "user_photo": "http://xxx.com"
        }
    ]
}
```

















