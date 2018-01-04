# 项目接口定义

## 1. 用户类 *User*
- *String* **usrname**
  - 用户登陆名，主键，长度：不超过30
- *String* **password** 
  - 用户密码，长度：不超过20
- *Date* **birthday** 
- *String* **sex** 
- *String* **Email**
- *Func* **update**(*Date* birth, *String* Sex, *String* Email) *return* *void*
  - 修改信息
- *Func* **passwdChange**(*String* newPasswd) *return* *void*
  - 修改密码
- *Func* **sendPost**(*Post* post) *return* *void*
  - 发帖
- *Func* **deletePost**(*String* postID) *return* *void* 
  - 删帖

## 2. 管理员类 *Administrator* : *User*
- *Override* **passwdChange**(*String* newPasswd) *return* *void*
- *Override* **deletePost**(*String* postID) *return* *void*
  - 删除帖子
- **appendPlate**(*String* postID) *return* *void*
  - 添加板块
- **removePlate**(*String* postID) *return* *void*
  - 删除板块

## 3. 帖子类 *Post*
- *String* postID
  - 帖子的身份标识，主键，长度：30
- *User* writer
  - 作者
- *Date* publishTime
  - 发表时间
- *String* content
  - 内容
- *Arraylist\<Comment\>* comments
  - 评论
- *Func* **appendComment**(*Comment* comment) *return* *void*
  - 添加评论
- *Func* **viewComment**(*Comment* comment) *return* *void*
  - 查看评论

## 4. 评论类 *Comment* : *Post*

## 5. 板块类 *Plate*
- *String* name
- *ArrayList\<Post\>* posts
- *Func* **getPosts**() *return* *void*

## 6. 总控制类 *ForumSystem*
- *Func* **usrSignIn**(*String* usrname,*String* passwd) *return* *void*
- *Func* **usrSignUp**(*User* user)
*return* *void*
- *Func* **ViewPost**()
*return* *void*

# 任务分工

## 1. 分支
- 主分支 *master*
  - 用于最终项目的展示
- 开发分支 *develop*
  - 用于最终项目的测试
- 钟婷 *Ting*
  - 负责用户类和管理员类和相关数据库的设计
- 姚悦 *Yao*
  - 负责帖子评论类和相关数据库的设计
- 仇思宇 *Qiu*
  - 负责页面前端的设计

## 2. 时间安排
- 1. 2018年1月15日前
  - 完成各自分支的任务，并发布代码
- 2. 2018年1月24日前
  - 由贲晨星负责设计好PPT模板，要求浅色背景（白色最好），标题工整，并写好项目背景和概述
