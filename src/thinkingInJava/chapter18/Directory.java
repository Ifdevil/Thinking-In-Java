package thinkingInJava.chapter18;

import java.io.File;
import java.io.FilenameFilter;
import java.io.FilterInputStream;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 目录使用工具
 */
public class Directory {

    public static File[] local(File dir,final String regex){
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
    }

    public static File[] local(String path,final String regex){
        return local(new File(path),regex);
    }

    public static class TreeInfo implements Iterable<File>{

        public List<File> files = new ArrayList<>();
        public List<File> dirs = new ArrayList<>();
        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }
        void addAll(TreeInfo other){
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }
        public String toString(){
            return "dirs: "+PPrint.pformat(dirs)+
                    "\n\n files: "+PPrint.pformat(files);
        }
    }
    public static TreeInfo walk(String start,String regex){
        return recurseDirs(new File(start),regex);
    }
    public static TreeInfo walk(File start,String regex){
        return recurseDirs(start,regex);
    }
    public static TreeInfo walk(File start){
        return recurseDirs(start,".*");
    }
    public static TreeInfo walk(String start){
        return recurseDirs(new File(start),".*");
    }

    static TreeInfo recurseDirs(File startDir,String regex){
        TreeInfo result = new TreeInfo();
        for (File item:startDir.listFiles()){
            if(item.isDirectory()){
                result.dirs.add(item);
                result.addAll(recurseDirs(item,regex));
            }else{
                if(item.getName().matches(regex)){
                    result.files.add(item);
                }
            }
        }
        return result;
    }

    public static class PPrint{
        public static String pformat(Collection<?> c){
            if(c.size()==0){return "[]";}
            StringBuilder result = new StringBuilder("[");
            for (Object item:c){
                if(c.size()!=1){
                    result.append("\n ");
                }
                result.append(item);
            }
            if(c.size()!=1){
                result.append("\n");
            }
            result.append("]");
            return result.toString();
        }
        public static void pprint(Collection<?> c){
            System.out.println(pformat(c));
        }
        public static void ppring(Object[] c){
            System.out.println(pformat(Arrays.asList(c)));
        }
    }
    public static void main(String[] args) {
        System.out.println(walk("."));
    }
}
