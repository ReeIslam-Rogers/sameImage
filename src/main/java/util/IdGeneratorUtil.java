package util;

import java.util.HashMap;

public class IdGeneratorUtil {
    private static HashMap<Class, Long> nextId = new HashMap<>();

    private IdGeneratorUtil(){}

    public static long next(Class cls){
        if (nextId.containsKey(cls)){
            long id = nextId.get(cls);
            nextId.put(cls,id+1);
            return id;
        } else {
            nextId.put(cls, 2L);
            return 1;
        }
    }
}
