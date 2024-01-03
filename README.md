1. merge a diff between local and remote, please remember even you are in a subdir, you still operate on the whole repository
   1. git branch:  check current branch
   2. git fetch origin:   get latest from github
   3. git diff origin/master:  diff local and github on master
   4. git checkout :  checkout local master
   5. git merge origin/master: merge local default with origin/master
   6. git push origin/master: push local to remote
      
