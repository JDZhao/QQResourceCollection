package course.storage.interfaces;

import course.storage.configuration.ConfigUration;

/**
 * 资源线程计算接口
 * 
 * @author zhaojd
 * @date 2016年10月17日 下午10:55:23
 * @version 1.0
 */
public interface IResourceThreadCount {
	public String threadCount(String uri, ConfigUration confing);
}
