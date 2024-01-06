1. merge a diff between local and remote, please remember even you are in a subdir, you still operate on the whole repository
   1. git branch:  check current branch
   2. git fetch origin:   get latest from github
   3. git diff origin/master:  diff local and github on master
   4. git checkout :  checkout local master
   5. git merge origin/master: merge local default with origin/master
   6. git push origin/master: push local to remote
  
2. run java project in eclipse
   1. you need to install jRE (java runtime enviroment) in your machine. all the files of a JRE are under a root dir.
   2. you can specify your installed JRE in eclipse. it is in Preferences/Java/installed JREs.
   3. create a java project. if eclipse cannot locate JRE, build path will show error.
   4. if you new a package, eclipse will create a hierachy of dirs corresponding to package name.
   5. import a new package, import reactor.core.publisher.Flux, error will occur when eclipse cannot resolve symbols and it will give you same solutions, you choose fix project setup and eclipse will search through .m2 to find the package. So it is better to install package from Mavern. so Mavern will keep the dependencies locally and can be used by eclipse projects    
