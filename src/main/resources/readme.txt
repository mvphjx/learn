git config --global user.name "你的名字或昵称"

git remote add origin  https://git.oschina.net/mvphjx/hanjx.git
git remote add origin  git@git.oschina.net:mvphjx/hanjx.git
git clone https://git.oschina.net/mvphjx/hanjx.git
git clone git@git.oschina.net:mvphjx/hanjx.git

git pull origin master
git touch init.txt //如果已经存在更改的文件,则这一步不是必须的
git status
git add .
git commit -m "第一次提交"
git push origin master

 git push 提示 Everything up-to-date
 需要先执行  add commit


git remote add origin https://git.oschina.net/mvphjx/Hanjx.git
git clone https://git.oschina.net/mvphjx/Hanjx.git

//下载  文件夹下代码
git checkout src