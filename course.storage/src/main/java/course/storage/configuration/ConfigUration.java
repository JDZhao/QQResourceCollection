package course.storage.configuration;

import java.io.Serializable;

/**
 * @author zhaojd
 * @date 2016��10��17�� ����11:25:54
 * @version 1.0
 */
public class ConfigUration implements Serializable {
	private static final long serialVersionUID = -8392856982624487074L;
	//�߳���Ĭ��ֵ
	private static  final int DTFAULT_SIZE = 100;
	// Ĭ�ϳ��Ի�ȡhtml����
	private static final int DTFAULT_TIMES = 3;
	
	private static final int DTFAULT_SLEPP = 50000;
	// �߳�����
	private int size;
	// ��ŵ�ַ
	private String path;
	//���û�ȡHTML����
	private int trytimes;
	//˯��ʱ��
	private int sleepTime;

	public ConfigUration() {
		this.size = DTFAULT_SIZE;
		this.trytimes = DTFAULT_TIMES;
		this.sleepTime = DTFAULT_SLEPP;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getTrytimes() {
		return trytimes;
	}

	public void setTrytimes(int trytimes) {
		this.trytimes = trytimes;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	
}
