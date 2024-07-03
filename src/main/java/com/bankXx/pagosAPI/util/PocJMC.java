package com.bankXx.pagosAPI.util;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PocJMC {

	private static final Object LOCK1 = new Object();
	private static final Object LOCK2 = new Object();
	private List<byte[]> memoryLeakList = new ArrayList<>();

	public void createDeadlock() {
		Thread thread1 = new Thread(() -> {
			synchronized (LOCK1) {
				log.info("Thread 1: Manteniendo LOCK1...");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				log.info("Thread 1: Esperando el LOCK2...");
				synchronized (LOCK2) {
					log.info("Thread 1: Manteniendo LOCK1 y LOCK2...");
				}
			}
		});

		Thread thread2 = new Thread(() -> {
			synchronized (LOCK2) {
				log.info("Thread 2: Manteniendo LOCK2...");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				log.info("Thread 2: Esperando el LOCK1...");
				synchronized (LOCK1) {
					log.info("Thread 2: Manteniendo LOCK2 y LOCK1...");
				}
			}
		});

		thread1.start();
		thread2.start();
	}

	public void createMemoryLeak() {
		while (true) {
			byte[] memoryChunk = new byte[1024 * 1024]; // 1MB
			memoryLeakList.add(memoryChunk);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
