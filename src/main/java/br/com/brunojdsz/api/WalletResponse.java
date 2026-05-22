package br.com.brunojdsz.api;

import br.com.brunojdsz.entities.Share;

import java.util.List;

public record WalletResponse(List<Share> shares) {
}
