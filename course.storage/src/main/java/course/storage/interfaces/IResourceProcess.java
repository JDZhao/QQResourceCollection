package course.storage.interfaces;

import java.util.concurrent.Callable;



/** 
 * 多线程资源处理的接口
* @author zhaojd
* @date 2016年10月24日 下午10:41:59 
* @version 1.0 
*/
public interface  IResourceProcess<T> extends Callable<T>{
}
