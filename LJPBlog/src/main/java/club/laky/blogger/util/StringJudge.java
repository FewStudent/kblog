package club.laky.blogger.util;

/**
 * @author  lakyjpan
 * */
public class StringJudge {

    /**
     * 判断字符串是否为空
     * */
    public static boolean isNull(String obj){
        if(obj == null || "".equals(obj)) {
            return true;
        }
        return false;
    }

    /**
     * 判断数字是否为空
     * */
    public static boolean isNumber(Integer obj){
        if(obj == null){
            return true;
        }
        return false;
    }

    //TODO
}
