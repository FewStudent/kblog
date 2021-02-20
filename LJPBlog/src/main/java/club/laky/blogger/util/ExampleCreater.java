package club.laky.blogger.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public class ExampleCreater<T> {

    public static List equalsOneFiled(Mapper mapper, Class cls, String filed, Object value){
        Example example = new Example(cls);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(filed,value);
        return mapper.selectByExample(example);
    }

    public static  List likeOneFiled(Mapper mapper, Class cls, String filed, String value){
        Example example = new Example(cls);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike(filed,value+"%");
        return mapper.selectByExample(example);
    }
}
