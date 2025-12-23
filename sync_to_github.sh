#!/bin/bash
cd ~/reader_src

# 1. 确保是一个 git 仓库
if [ ! -d ".git" ]; then
    git init
fi

# 2. 添加所有文件
git add .

# 3. 提交更改（如果没有提交，push 会失败）
git commit -m "Initial sync or update: $(date +'%Y-%m-%d %H:%M:%S')"

# 4. 获取当前本地分支的名称
CURRENT_BRANCH=$(git rev-parse --abbrev-ref HEAD)

# 5. 设置远程仓库地址
git remote remove origin 2>/dev/null
git remote add origin https://github.com/Transwarpcom/reader

# 6. 强制推送当前分支到远程 main 分支
# 注意：这里将本地分支强制推送到远程的 main
git push origin $CURRENT_BRANCH:main --force
