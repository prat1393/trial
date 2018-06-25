package com.itko.lisa.ext.util;

import com.itko.lisa.vse.stateful.model.Request;
import com.itko.lisa.vse.stateful.model.Transaction;
import com.itko.lisa.vse.stateful.model.TransactionNode;

public class CustomTransactionNode extends TransactionNode {

	public void setRequest(Request req) {
		this.request=req;
	}

	public CustomTransactionNode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomTransactionNode(Transaction arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
}
