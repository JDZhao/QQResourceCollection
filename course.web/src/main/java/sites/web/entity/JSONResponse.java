package sites.web.entity;

/**
 * @author zhaojd
 * @date 2016年10月27日 下午3:04:03
 * @version 1.0
 */
public class JSONResponse {
	private int status;
	private String desc;
	private Object data;

	public static JSONResponse  succeed(Object data) {
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setStatus(1);
		jsonResponse.setData(data);
		return jsonResponse;
	}
	
	public static JSONResponse error(Object data){
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setStatus(-1);
		jsonResponse.setData(data);
		return jsonResponse;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JSONResponse [status=" + status + ", desc=" + desc + ", data=" + data + "]";
	}

}
