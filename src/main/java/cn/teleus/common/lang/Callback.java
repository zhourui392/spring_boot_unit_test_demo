package cn.teleus.common.lang;

/**
 * 
 * @author zhourui(zhourui0125@gmail.com)
 * @param <T>
 */
public interface Callback<T> {
	 void invoke(T obj);
}