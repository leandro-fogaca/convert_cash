package com.convertcash.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.convertcash.domain.Cash;
import com.convertcash.domain.User;
import com.convertcash.domain.Wallet;
import com.convertcash.repository.CashRepository;
import com.convertcash.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CashRepository cashRepository;

	@Override
	public void run(String... arg0) throws Exception {

		userRepository.deleteAll();
		cashRepository.deleteAll();

		Cash dollar = new Cash(1, "Dollar", 1000.00);
		Cash real = new Cash(2, "Real", 2000.00);
		User leandro = new User(null, "Leandro Fogaça", "leandro-fogaca87@gmail.com", "12345", new Wallet(real));
		User leandro2 = new User(null, "Leandro Fogaça", "leandro-fogaca87@gmail.com", "12345", new Wallet(dollar));

		userRepository.save(leandro);
		cashRepository.save(dollar);
	}

}
