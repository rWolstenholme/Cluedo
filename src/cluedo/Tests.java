package cluedo;

import static org.junit.Assert.*;

import org.junit.Test;

public class Tests {

	@Test
	public void playerCount() {
		Cluedo c = new Cluedo(3);
		assert(c.getpCount()==3);
	}

	@Test
	public void cardsInHands() {
		Cluedo c = new Cluedo(3);
		for(Card card:c.getDeck()){
			boolean found = false;
			for(Player p:c.getPlayers()){
				if(p.getHand().contains(card)){found = true;}
			}
			if(!found){fail("A card was not distributed to a player");}
		}
	}
	
	@Test
	public void moving() {
		Cluedo c = new Cluedo(3);
		Player p = c.getPlayers().get(1);
		Location startsAt = p.getAtLoc();
		assert(c.getBoard().move(p, 1, 0));
		assert(c.getBoard().getPlayerLoc(p).getX()-1==startsAt.getX());
		assert(c.getBoard().getPlayerLoc(p).getY()==startsAt.getY());
		assert(p.getAtLoc()==c.getBoard().getPlayerLoc(p));
	}
	
	@Test
	public void invalidMove() {
		Cluedo c = new Cluedo(3);
		Player p = c.getPlayers().get(1);
		assert(!c.getBoard().move(p, 50, 10));
	}
	
	@Test
	public void mCardNotInHand() {
		Cluedo c = new Cluedo(3);
		for(Player p:c.getPlayers()){
			for(Card card:c.getMCards()){
				if(p.getHand().contains(card)){fail("Murder card was in a players hand");}
			}
		}
	}
	
	
}
