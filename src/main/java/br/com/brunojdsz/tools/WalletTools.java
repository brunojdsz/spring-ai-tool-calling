package br.com.brunojdsz.tools;

import br.com.brunojdsz.entities.Share;
import br.com.brunojdsz.repositories.WalletRepository;
import org.springframework.ai.tool.annotation.Tool;

import java.util.List;

public class WalletTools {

    private WalletRepository repository;

    public WalletTools(WalletRepository repository) {
        this.repository = repository;
    }

    @Tool(description = "Number of shares for each company in my wallet")
    public List<Share> getNumberOfShares(){
        return repository.findAll();
    }
}
