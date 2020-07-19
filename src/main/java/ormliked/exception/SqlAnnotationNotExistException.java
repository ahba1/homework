package ormliked.exception;

/**
 * this exception occurs when you invoke some sql method without sql annotation
 * created by zhouliang on 2020/7/19
 * @author zhouliang
 * @version 1.0
 */
public class SqlAnnotationNotExistException extends BaseSqlException {

    public SqlAnnotationNotExistException(){
        this("the invoked method without sql annotation");
    }

    public SqlAnnotationNotExistException(String msg){
        super(msg);
    }
}
