package ormliked.exception;

public class UnsupportedSqlOperationException extends BaseSqlException {

    public UnsupportedSqlOperationException(){
        this("sql operation is unsupported, the illegal sql operations are \"select, insert, delete, update\"");
    }

    public UnsupportedSqlOperationException(String msg){
        super(msg);
    }
}
