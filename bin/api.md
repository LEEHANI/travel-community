## LoginController

로그인과 회원가입에 대한 컨트롤러

### GET /register : 회원가입 페이지를 보여준다.

- method    : `getRegisterForm()`

**success**

- view		: `member/register`

### POST /register : 회원가입을 처리한다.

- method       		: `processRegisterForm()`
- parameter 		: `MemberDto memberDto`

**success**

- redirect 			: `/`

**fail**

- view				: `member/register`
- model				: `String error`

### GET /login : 로그인 페이지를 보여준다.

- method       		: `getLoginForm()`

**success**

- view 				: `member/login`

### POST /login : 로그인을 처리한다.

- method       		: `processLoginForm()`
- parameter 		: `MemberDto`

**success**

- redirect			: `/`

**fail**				

- view			: `member/login`
- model			: `String error`

### GET /logout : 로그아웃을 처리한다.

- method       		: `processLogout()`

**success**

- redirect			: `/`

<br><hr>

## MemberController

### GET /mypage/{info} : 마이 페이지를 보여준다.

- method       		: `getMypage()`
- parameter			: `int page`

**success**

- `/mypage/member`
  - view			: `member/mypage/member`
  - model			: `Member member`, `int like`

- `/mypage/{article|bookmark}`
  - view			: `member/mypage/{article|bookmark}`
  - model			: `List<Article> list`, `Page page` 

- `/mypage/comment`
  - view			: `member/mypage/comment`
  - model			: `List<Comment> list`, `Page page`

- `/mypage/temp_article`
  - view			: `member/mypage/temp_article`
  - model			: `List<Article> list`, `Page page`
  
**fail**

- view			: `member/login`
  
### GET /mypage/auth : 회원정보 변경 전에 회원 인증 페이지를 보여준다.

- method		: `getAuthForm()`

**success**

- view          : `member/mypage/auth`

**fail**

- view          : `member/login`

### POST /mypage/auth : 회원정보 변경 전에 회원 인증을 처리한다.

- method        : `isOwner()`

**success**

- redirect      : `/mypage/{update|delete}`
- session		: `Boolean isOwner`

**fail**

- view          : `member/mypage/auth`
- model			: `String error`

### GET /mypage/update : 마이 페이지의 회원정보 변경 페이지를 보여준다.

- method       		: `getUpdateMypageForm()`

**success**	

- view              : `member/mypage/update`

**fail**

- view	        	: `member/mypage/auth`
- session			: `String prevPage`

### POST /mypage/update : 회원 정보 변경을 처리한다. 

- method       		: `processUpdateMemberForm()`
- parameter 		: `MemberDto memberDto`

**success**

- redirect      	: `/mypage/member`


**fail**

- view      		: `member/mypage/update`
- model				: `String error`

### GET /mypage/delete : 회원 정보를 삭제한다.

- method			: `deleteMember()`

**success**

- redirect			: `/`

**fail**

- view				: `member/mypage/auth`
- session			: `String prevPage`

<br><hr>

## ArticleController

### GET /{category}/{nation} : {category}에 해당하는 게시물 중 {nation}에 대한 글만을 보여준다.

- method			: `getBoardByCategoryAndNation()`
- parameter			: `int page`, `Sort sort`, `String search`

**success**

- view				: `article/list`
- model				: `List<Article> list`, `Page page`

### GET /{category}/write : {category}에 대한 글쓰기 폼을 보여준다.

- method			: `getCreationForm()`

**success**

- view				: `article/write`

**fail**

- view				: member/login

### POST /{category}/write : {category}에 대한 글쓰기 요청을 처리한다.

- method			: `processCreationForm()`
- parameter			: `Article article`

**success**

- redirect			: `/article/{articleId}`
 
**fail**

- view				: `article/write`
- model				: `String error`

### GET /article/{articleId} : articleId에 대한 상세보기 페이지를 보여준다.

- method 			: `showArticle()`

**success**

- view				: `article/detail`

**fail**

- redirect			: `/`

### GET /article/update/{articleId} : articleId에 대한 업데이트 페이지를 보여준다.

- method			: `getUpdateArticleForm()`

**success**

- view				: `article/update`
- model				: `ArticleDto article`

**fail**

- redirect			: `/`

### POST /article/update/{articleId} : articleId에 대한 업데이트를 처리한다.

- method			: `processUpdateArticleForm()`
- parameter			: `Article article`

**success**

- redirect			: `/article/{articleId}`

**fail**

- view				: `article/update` 

### GET,POST /article/delete/{articleId} : articleId에 해당하는 Article을 삭제 처리한다.

- method			: `deleteArticle()`

**success**

- redirect			: `/`

**fail**

- redirect			: `/`

### GET /article/like : 게시물에서 좋아요를 클릭하면, 비동기적으로 좋아요를 반영하여 응답한다.

- method			: `processLikeArticle()`
- parameter			: `Long articleId`, `int liked`

**success**

- status			: `200`
- body				: `int liked`

**fail**

- status			: `302`
- body				: `/login`

## UploadFileController

### GET /image/{fileId} : fileId에 해당하는 image를 response body에 담아 전달한다.

- method			: `getFile()`

**success**

- status			: `200`
- body				: `Resource resource`

**fail**			

- status			: `400`

### POST /image		: 전달받은 MultipartFile을 디스크와 디비에 저장한다.

- method			: `imageUpload()`
- parameter			: `MultipartFile file`

**success**

- status			: `200`
- body				: `/image/{fileId}`

**fail**

- status			: `400`