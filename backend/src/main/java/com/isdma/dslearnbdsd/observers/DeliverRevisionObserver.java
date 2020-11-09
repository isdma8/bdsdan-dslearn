package com.isdma.dslearnbdsd.observers;

import com.isdma.dslearnbdsd.entities.Deliver;

public interface DeliverRevisionObserver {

	
	void onSaverevision(Deliver deliver); //on é um padrao muito usado em eventos, aqui no caso quando executar a correção eu quero fazer alguma coisa
}
