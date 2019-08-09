package com.ecommerce;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.StripeException;
import com.ecommerce.dao.ClientDAO;
import com.ecommerce.models.Client;
import com.ecommerce.models.Order;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

public class PaymentService {
	
	private static final String TEST_STRIPE_SECRET_KEY = "sk_test_fAkFCbx2crnmAgHlYduSIfcl";

	  public PaymentService() {
	    Stripe.apiKey = TEST_STRIPE_SECRET_KEY;
	  }

	  public String createCustomer(Client client) {
		
	    Map<String, Object> customerParams = new HashMap<String, Object>();
	    customerParams.put("description", 
	      client.getFirstName() + " " + client.getLastName());
		customerParams.put("email", client.getEmail());
			
		String id = null;
			
		try { 
	      // Create customer
		  Customer stripeCustomer = Customer.create(customerParams);
		  id = stripeCustomer.getId();
		  client.setStripeID(id);
		  (new ClientDAO()).updateClient(client);
		  System.out.println(stripeCustomer);
		} catch (CardException e) {
		  // Transaction failure
		} catch (RateLimitException e) {
		  // Too many requests made to the API too quickly
		} catch (InvalidRequestException e) {
		  // Invalid parameters were supplied to Stripe's API
		} catch (AuthenticationException e) {
		  // Authentication with Stripe's API failed (wrong API key?)
		} catch (ApiConnectionException e) {
		  // Network communication with Stripe failed
		} catch (StripeException e) {
		  // Generic error
		} catch (Exception e) {
		// Something else happened unrelated to Stripe
		}
		
	    return id;	
	  }

	  public boolean chargeCreditCard(Order order, String token) {
		boolean paid = false;		
	    // Stripe requires the charge amount to be in cents
	    int chargeAmountCents = (int)Math.round(order.getTotalPrice() * 100);

	    Client user = null;
		try {
			user = (new ClientDAO().getClientByUsername(order.getUsername()));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("amount", chargeAmountCents);
		chargeParams.put("currency", "usd");
		chargeParams.put("description", "New Order");		
		chargeParams.put("source", token);
		chargeParams.put("receipt_email", user.getEmail());

		Map<String, String> metadata = new HashMap<>();
		String order_id = String.valueOf(order.getOrder_id());
		metadata.put("order_id", order_id);
		chargeParams.put("metadata", metadata);
				
		try {
		  // Submit charge to credit card 
		  Charge.create(chargeParams);
		  paid  = true;
	    } catch (CardException e) {
		  // Transaction was declined
		  System.out.println("Status is: " + e.getCode());
		  System.out.println("Message is: " + e.getMessage());
		} catch (RateLimitException e) {
		  // Too many requests made to the API too quickly
		} catch (InvalidRequestException e) {
		  // Invalid parameters were supplied to Stripe's API
	    } catch (AuthenticationException e) {
		  // Authentication with Stripe's API failed (wrong API key?)
		} catch (ApiConnectionException e) {
		  // Network communication with Stripe failed
		 } catch (StripeException e) {
		  // Generic error
		} catch (Exception e) {
		  // Something else happened unrelated to Stripe
		}	
		return paid;
	  }
	}



