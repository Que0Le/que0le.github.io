
### Init commands

```bash
hugo new site blog
cd blog
git init
git submodule add -f git@github.com:Que0Le/hugo-theme-hello-friend.git  themes/hello-friend
hugo server -t hello-friend

hugo new posts/my-first-post.md
# publish to /public folder
hugo
# start server
cd public
python2 -m SimpleHTTPServer 9000
```