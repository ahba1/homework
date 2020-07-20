package ormliked.exception;

/**
 * this exception occurs when do some unexpected sql operation or the method's return type is not supported
 * created by zhouliang on 2020/7/20
 * @author zhouliang
 * @version 1.0
 */
public class UnsupportedSqlOperationException extends BaseSqlException {

    public UnsupportedSqlOperationException(){
        this("sql operation is unsupported, the illegal sql operations are \"select, insert, delete, update\"");
    }

    public UnsupportedSqlOperationException(String msg){
        super(msg);
    }
}
