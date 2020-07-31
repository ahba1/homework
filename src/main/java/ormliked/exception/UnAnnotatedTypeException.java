package ormliked.exception;

public class UnAnnotatedTypeException extends BaseSqlException {

    public UnAnnotatedTypeException(){
        this("the interface is not annotated with Sql");
    }

    public UnAnnotatedTypeException(String msg){
        super(msg);
    }
}
