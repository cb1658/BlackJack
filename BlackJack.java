package bj;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BlackJack {
	
	private int player_money = 10; 
	private ArrayList<Integer> player_cards =  new ArrayList<Integer>();
	private ArrayList<Integer> dealer_cards = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		BlackJack bj = new BlackJack();
		bj.initialize();
		
		Scanner sc = new Scanner(System.in);
		
		boolean continue_ = true;

		while(continue_) {
			String input = "";
			
			System.out.println("Would you like to draw again? (y/n/dd/SURR)");
			input = sc.nextLine();
			
			if(input.equals("y")) {
				bj.setPlayerCards(add_card(bj.getPlayerCards()));
				System.out.println(print_cards(bj.getPlayerCards()));
				
				if(addup_cards(bj.getPlayerCards(),0) > 21) {
					System.out.println("BUST!! You lost your money.");
					
					bj.setPlayerMoney(bj.getPlayerMoney()-2);
					
					if(bj.getPlayerMoney() <= 0) {
						System.out.println("--------------------");
						System.out.println("GAME OVER! YOU RAN OUT OF MONEY!");
						System.exit(-1);
					}
					
					System.out.println("You have " + bj.getPlayerMoney() + " money left");
					
					System.out.println("----------------------");
					
					bj.initialize();
				}
				
			}
			if(input.equals("n")) {
				
				int locked_in;
				
				locked_in = addup_cards(bj.getPlayerCards(),0);
				
				if(numberOfA(bj.getPlayerCards()) >= 1 && addup_cards(bj.getPlayerCards(),1) <= 21) {
					locked_in = addup_cards(bj.getPlayerCards(),1);
				}
				
				System.out.println("Locked in at a sum of " + locked_in);
				
				System.out.println("Dealer cards REVEALED: " + print_cards(bj.getDealerCards()));
				
				boolean continue_steps = true;
				
				while(dealer_algorithm(addup_cards(bj.getDealerCards(),0), locked_in, numberOfA(bj.getDealerCards())) == true && continue_steps) {
					bj.setDealerCards(add_card(bj.getDealerCards()));
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println(print_cards(bj.getDealerCards()));
					
					if(addup_cards(bj.getDealerCards(),0) > 21) {
						System.out.println("The dealer BUSTED! You get more money!");
						
						bj.setPlayerMoney(bj.getPlayerMoney()+2);
						
						System.out.println("You have " + bj.getPlayerMoney() + " money left");
						
						System.out.println("-------------------------");

						bj.initialize();
						
						continue_steps = false; 
					}
				}
				
				if(addup_cards(bj.getDealerCards(),0) > locked_in && continue_steps) {
					System.out.println("You lost your money.");
					bj.setPlayerMoney(bj.getPlayerMoney()-2);
					
					
					System.out.println("You have " + bj.getPlayerMoney() + " money left");
					System.out.println("--------------------------");
					if(bj.getPlayerMoney() <= 0) {
						System.out.println("--------------------------");
						System.out.println("GAME OVER! YOU RAN OUT OF MONEY!");
						System.exit(-1);
					}
				}
				
				if(addup_cards(bj.getDealerCards(),0) < locked_in && continue_steps) {
					System.out.println("You won money!");
					bj.setPlayerMoney(bj.getPlayerMoney()+2);
					
					System.out.println("You have " + bj.getPlayerMoney() + " money left");
					System.out.println("--------------------------");
				}
				
				if(addup_cards(bj.getDealerCards(),0) == locked_in && continue_steps) {
					System.out.println("Draw: You lost no money");
					System.out.println("--------------------------");
				}
				
				if(continue_steps) {
					bj.initialize();
				}
			}
			
			if(input.equals("dd")) {
				
				boolean continue_steps = true;
				
				bj.setPlayerCards(add_card(bj.getPlayerCards()));
				
				System.out.println(print_cards(bj.getPlayerCards()));
				
				if(addup_cards(bj.getPlayerCards(),0) > 21) {
					System.out.println("BUST!! You lost your money.");
					
					continue_steps = false; 
					
					bj.setPlayerMoney(bj.getPlayerMoney()-4);
					
					System.out.println("You have " + bj.getPlayerMoney() + " money left");
					
					System.out.println("--------------------------");
					
					if(bj.getPlayerMoney() <= 0) {
						System.out.println("--------------------");
						System.out.println("GAME OVER! YOU RAN OUT OF MONEY!");
						System.exit(-1);
					}
					
					bj.initialize();
				}
				
				int locked_in;
				
				locked_in = addup_cards(bj.getPlayerCards(),0);
				
				if(numberOfA(bj.getPlayerCards()) >= 1 && addup_cards(bj.getPlayerCards(),1) <= 21) {
					locked_in = addup_cards(bj.getPlayerCards(),1);
				}
				
				if(continue_steps) {
					print_cards(bj.getPlayerCards());
					
					System.out.println("Locked in at a sum of " + locked_in);
					
					System.out.println("Dealer cards REVEALED: " + print_cards(bj.getDealerCards()));
				}
				
				while(dealer_algorithm(addup_cards(bj.getDealerCards(),0), locked_in, numberOfA(bj.getDealerCards())) == true && continue_steps) {
					bj.setDealerCards(add_card(bj.getDealerCards()));
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println(print_cards(bj.getDealerCards()));

					if(addup_cards(bj.getDealerCards(),0) > 21) {
						System.out.println("The dealer BUSTED! You get more money!");
						
						bj.setPlayerMoney(bj.getPlayerMoney()+4);
						
						System.out.println("You have " + bj.getPlayerMoney() + " money left");
						
						System.out.println("-------------------------");

						bj.initialize();
						
						continue_steps = false; 
					}
				}
				
				if(addup_cards(bj.getDealerCards(),0) > locked_in && continue_steps) {
					System.out.println("You lost your money.");
					bj.setPlayerMoney(bj.getPlayerMoney()-4);
					
					
					System.out.println("You have " + bj.getPlayerMoney() + " money left");
					System.out.println("--------------------------");
					if(bj.getPlayerMoney() <= 0) {
						System.out.println("--------------------------");
						System.out.println("GAME OVER! YOU RAN OUT OF MONEY!");
						System.exit(-1);
					}
				}
				
				if(addup_cards(bj.getDealerCards(),0) < locked_in && continue_steps) {
					System.out.println("You won money!");
					bj.setPlayerMoney(bj.getPlayerMoney()+4);
					
					System.out.println("You have " + bj.getPlayerMoney() + " money left");
					System.out.println("--------------------------");
				}
				
				if(addup_cards(bj.getDealerCards(),0) == locked_in && continue_steps) {
					System.out.println("Draw: You lost no money");
					System.out.println("--------------------------");
				}
				
				if(continue_steps) {
					bj.initialize();
				}
			}
			if(input.equals("SURR")) {
				System.out.println("So much for 'never surrender never give up.' Welp.");
				bj.setPlayerMoney(bj.getPlayerMoney()-1);
				System.out.println("You have " + bj.getPlayerMoney() + " money left");
				System.out.println("-------------------------");
				if(bj.getPlayerMoney() <= 0) {
					System.out.println("--------------------------");
					System.out.println("GAME OVER! YOU RAN OUT OF MONEY!");
					System.exit(-1);
				}
				
				bj.initialize();
			}
		}
	}
	
	public void initialize() {
		player_cards.clear();
		
		player_cards = add_card(player_cards);
		player_cards = add_card(player_cards);
		
		dealer_cards.clear();
		
		dealer_cards = add_card(dealer_cards);
		dealer_cards = add_card(dealer_cards);
		
		System.out.println(print_cards(player_cards));
		System.out.println("The dealer's cards are: " + dealer_cards.get(0) + " | ???");
	}
	
	public static ArrayList<Integer> add_card(ArrayList<Integer> al) {
		
		int[] cards = new int[] {1,2,3,4,5,6,7,8,9,10,10,10,10};
		
		Random r = new Random();
		int ra = r.nextInt(13);
		
		al.add(cards[ra]);
		
		return al;
	}
	
	public static String print_cards(ArrayList<Integer> al) {
		StringBuilder bob = new StringBuilder();
		bob.append("Cards are: " + al.get(0));
		
		for(int i = 1; i < al.size(); i++) {
			bob.append(" | " + al.get(i));
		}
		
		return bob.toString();
	}
	
	public static int addup_cards(ArrayList<Integer> al, int numOfA) {
		
		int total = 0;
		
		for(int i = 0; i < al.size(); i++) {
			total += al.get(i);
		}
		
		return total + 10*numOfA;
	}
	
	public static boolean dealer_algorithm(int card_sum, int player_sum, int num_of_A) {
		// If the function returns true, the dealer draws a card. If false, he doesn't. 
		
		if(card_sum > 16) {
			return false;
		}			
		
		else if(card_sum < 17 && card_sum < player_sum) {
			return true;			}
		
		
		else {
			return false;			}

	}
	
	public static int numberOfA(ArrayList<Integer> al) {
		int numOfA = 0;
		
		for(int i = 0; i < al.size(); i++) {
			if(al.get(i) == 1) {
				numOfA++;
			}
		}
		
		return numOfA;
	}
	
	public ArrayList<Integer> getPlayerCards(){
		return this.player_cards;
	}
	
	public ArrayList<Integer> getDealerCards(){
		return this.dealer_cards;
	}
	
	public int getPlayerMoney() {
		return this.player_money;
	}
	
	public void setPlayerCards(ArrayList<Integer> al) {
		this.player_cards = al;
	}
	
	public void setDealerCards(ArrayList<Integer> al) {
		this.dealer_cards = al;
	}
	
	public void setPlayerMoney(int a) {
		this.player_money = a;
	}
}

/*

v0.1-v0.2:
Helper functions created and bj.initialize() is working

v0.3:
"Yes" option to draw again question has been added

v1.0:
"No" option to draw again question has been added

v1.0.1:
Member variables encapsulated

v1.1:
Added functionality to the A card for the player

v1.1.1: 
Fixed an issue in the "no" circumstance where the game auto-plays itself for a round before returning to normal

v1.2:
"Double down" and "surrender" option to draw again question has been added

v1.2.1:
Fixed an issue in the "double down" circumstance where a new game starts before the old game ends
Code for detecting A card's addup has been optimized

v1.2.2 (IN PROGRESS):
Added functinality to the A card for the dealer
Slight time delay for dealer cards

THINGS TO BE DONE:
-Add insurance [v1.2.3]

*/