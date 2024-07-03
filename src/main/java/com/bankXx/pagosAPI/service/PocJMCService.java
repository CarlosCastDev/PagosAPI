package com.bankXx.pagosAPI.service;

import org.springframework.stereotype.Service;

import com.bankXx.pagosAPI.util.PocJMC;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PocJMCService {

//	1: para provocar memoryleak
//	2: para provocar deadlock
	public void test(int opc) {
		PocJMC example = new PocJMC();
		switch (opc) {
		case 1:
			example.createMemoryLeak();
			break;
		case 2:			
			example.createDeadlock();
			break;
		default:
			log.info("opcion no valida: " + opc);
		}

	}

}
