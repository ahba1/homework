package ormliked.exception;

/**
 * this exception occurs when the query has many results while the function return type is not a list or an array
 * created by zhouliang on 2020/7/19
 * @author zhouliang
 * @version 1.0
 */
public class ManyResultException extends BaseSqlException {

    public ManyResultException(){
        this("there are many results, but the return type is not a list or an array");
    }

    public ManyResultException(String msg){
        super(msg);
    }
}
