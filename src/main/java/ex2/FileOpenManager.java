package ex2;

import java.util.*;

public class FileOpenManager {
    Map<String, String> map = new HashMap<>();

    void register(String name, String extension) {
        map.put(extension.toLowerCase(), name);
    }

    String getName(String extension) {
        return map.get(extension.toLowerCase());
    }



    void removeConnection(String name, String extension) {

        map.remove(extension.toLowerCase(), name);
    }

    List<String> getAllExtensions() {
        Set<String> strings = map.keySet();
        List<String> list = new ArrayList<>(strings);
        AppsComparator comparator = new AppsComparator();
        list.sort(comparator);
        return list;
    }


    Set<String> getAllApps() {
        Collection<String> values = map.values();
        Set<String> appSet = new HashSet<>(values);
        return appSet;
    }

    public class AppsComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }


}
