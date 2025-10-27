package Example.Map;

import java.util.HashMap;

//为什么不用HashMap 命名，因为会报错
public class HashMaps {
    public static void main(String[] args) {
        //键和值都需要是Java classes. 不能是 primitive types.即 int,long,double,float都是错的
        HashMap<String, Integer> empIds = new HashMap<>();

        empIds.put("John", 12345);  
        empIds.put("Carl", 54321);
        empIds.put("Jerry", 8675309);

        System.out.println(empIds);  //{Carl=54321, John=12345, Jerry=8675309}
        System.out.println(empIds.get("Carl"));  //54321
        System.out.println(empIds.containsKey("George"));  // false
        System.out.println(empIds.containsValue(6));  //false

        //会改变john的value
        empIds.put("John", 98765);
        System.out.println(empIds);  // {Carl=54321, John=98765, Jerry=8675309}

        //即便hashmap中不存在，也不报错，只是没有什么操作
        empIds.replace("Kramer", 777);
        System.out.println(empIds);  // {Carl=54321, John=98765, Jerry=8675309}

        //john已经存在了，就不会再改变，也不会改变john的value，指的是如果不在，则添加
        empIds.putIfAbsent("John", 222);
        System.out.println(empIds);  // {Carl=54321, John=98765, Jerry=8675309}

        //Steve不在hashmap里面。所以就添加
        empIds.putIfAbsent("Steve", 222);
        System.out.println(empIds);  // {Steve=222, Carl=54321, John=98765, Jerry=8675309}

        //移除
        empIds.remove("Steve");
        System.out.println(empIds);  // {Carl=54321, John=98765, Jerry=8675309}
    }
}
