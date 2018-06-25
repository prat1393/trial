package com.itko.lisa.newfilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Collection;

import org.w3c.dom.Element;

import com.itko.lisa.core.ModuleLegacy;
import com.itko.lisa.resources.*;
import com.itko.util.Parameter;
import com.itko.util.ParameterList;
import com.itko.util.StrUtil;
import com.itko.util.StreamHelp;
import com.itko.util.XMLUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.OutputStream;

import com.itko.lisa.test.FilterBaseImpl;
import com.itko.lisa.test.FilterInterface;
import com.itko.lisa.test.StepConnection;
import com.itko.lisa.test.TestDefException;
import com.itko.lisa.test.TestExec;
import com.itko.lisa.test.TestRunException;
import com.itko.lisa.vse.stateful.model.Request;
import com.itko.lisa.vse.stateful.protocol.DataProtocol;

import com.itko.util.Parameter;
import com.itko.util.ParameterList;

public class CustomFilter implements FilterInterface {

	 private static final String FILE_PARAM = "file";
	  private static final String FILE_PARAM_DESC = ModuleLegacy.resources.get("test.fsavep2f.filedesc", new String[0]);
	  private String file;
	@Override
	public void writeXML(PrintWriter arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTypeName() throws Exception {
		// TODO Auto-generated method stub
		return "Custom filter to sql resultset to excel";
	}

	@Override
	public void gatherFilterStepConnections(String arg0, ParameterList arg1, Collection<StepConnection> arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ParameterList getParameters() {
		// TODO Auto-generated method stub
		ParameterList p = new ParameterList();
		p.addParameter( new Parameter(FILE_PARAM_DESC, "file", this.file, OutputStream.class));
	}

	@Override
	public String getValueToFilterPropKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize(Element arg0) throws TestDefException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isGlobalFilter() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isScopeGlobal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isScopeLocal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void markFilterAsGlobal(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean postFilter(TestExec ts) throws TestRunException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preFilter(TestExec arg0) throws TestRunException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setValueToFilterPropKey(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supportsDesignTimeExecution() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supportsDynamicResponseToFilter() {
		// TODO Auto-generated method stub
		return false;
	}

		




			  }

			

	}
	


