package authenticationMenager;

import mainMenuMenager.Listens;

/** 
 * Interface that will be implemented by main authentication screen.
 * 
 * @see LoginAndRegisterScreen :for concrete implementation.
 * @author Aykut Bir
 * @since 30/04/2024
 */

public interface LoginListener extends Listens{
	
	void onSuccessfulLogin(String userName);
}
