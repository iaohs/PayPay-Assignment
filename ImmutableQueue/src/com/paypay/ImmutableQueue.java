package com.paypay;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ImmutableQueue<T> implements Queue<T>{
	
	private final List<T> iqueue;
	private int q_size;
	
	ImmutableQueue(int size){
		if(size <= 0){
			throw new IllegalArgumentException("Queue size must be greater than 0");
		}
		this.q_size = size;
		iqueue = new ArrayList<T>();
	}
	
	private ImmutableQueue(List<T> tempList){
		iqueue = Collections.unmodifiableList(new ArrayList<T>(tempList));
	}
	
	@Override
	public Queue<T> enQueue(T t) {
		if (null == t){
			throw new IllegalArgumentException("Value is null");
		}
		if (iqueue.size() == this.q_size){
			throw new IllegalArgumentException("Queue is full");
		}
		List<T> tempList = new ArrayList<T>(iqueue);
		tempList.add(t);
		ImmutableQueue<T> new_iqueue =  new ImmutableQueue<T>(tempList);
		return new_iqueue;
	}
	
	@Override
	public Queue<T> deQueue() {
		if (iqueue.size() == 0){
			throw new IllegalArgumentException("Queue is empty");
		}
		List<T> tempList = new ArrayList<T>(iqueue);
		ImmutableQueue<T> new_iqueue =  new ImmutableQueue<T>(tempList.subList(1, tempList.size()));
		return new_iqueue;
	}
	
	@Override
	public T head() {
		return iqueue.size() > 0 ? iqueue.get(0) : null;
	}
	
	@Override
	public boolean isEmpty() {
		return iqueue.size() == 0 ? true : false;
	}
}
