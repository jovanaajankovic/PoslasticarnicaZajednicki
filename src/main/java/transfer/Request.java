package transfer;

import java.io.Serializable;

public class Request implements Serializable{
	
    private static final long serialVersionUID = 1L;
	
	 private int operation;
	    private Object data;

	    public Request() {
	    }

	    public Request(int operation, Object data) {
	        this.operation = operation;
	        this.data = data;
	    }

	    public Object getData() {
	        return data;
	    }

	    public int getOperation() {
	        return operation;
	    }

	    public void setData(Object data) {
	        this.data = data;
	    }

	    public void setOperation(int operation) {
	        this.operation = operation;
	    }

}
