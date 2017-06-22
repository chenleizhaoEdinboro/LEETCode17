// So, we directly return the last entry in the dd array. If the last level entry happens to be a directory, 
//we can obtain its subdirectory list from the list of keys in its dirsdirs hashmap. 
public class FileSystem {
    class Dir{
         HashMap<String, Dir> dirs;
         HashMap<String, String> files;

         public Dir(){
             dirs = new HashMap <>();
             files = new HashMap <>();
         }
    }

    Dir root;

    public FileSystem(){
        root = new Dir();
    }

    public List<String> ls(String path){
        Dir temp = root;
        List<String> resultFiles = new ArrayList<>();
       
        if(!path.equals("/")){
             String[] d = path.split("/");
             //处理中间是directory的情况
             for(int i=1; i < d.length-1; i++){
                temp = temp.dirs.get(d[i]);
             }
             //如果最后一个元素是文档，就返回文档的名称，如果是文件夹，就返回文件夹里所有的文件名称
             if(temp.files.containsKey(d[d.length-1])){
                resultFiles.add(d[d.length-1]);
                return resultFiles;
             }else{
                temp.dirs.get(d[d.length-1]);
             }
        }
        //Return the list of files in the last level of folder
        resultFiles.addAll(new ArrayList<>(temp.files.keySet()));
        resultFiles.addAll(new ArrayList<>(temp.dirs.keySet()));
        //Finally sort in lexicographic order
        Collectiions.sort(resultFiles);
        return resultFiles;
    }
    //Check each level and build the new directory
    public void mkdir(String path){
        Dir temp = root;
        String[] d = path.split("/");
        for(int i=0; i<d.length; i++){
            if(!temp.dirs.containsKey(d[i]));{
                temp.put(d[i], new Dir());
            }
          temp = temp.dirs.get(d[i]);
        }
    }
    
  
      
}