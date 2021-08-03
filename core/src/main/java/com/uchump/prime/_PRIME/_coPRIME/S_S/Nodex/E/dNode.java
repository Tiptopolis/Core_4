package com.uchump.prime._PRIME._coPRIME.S_S.Nodex.E;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Position;
import javax.swing.text.Segment;

import javax.swing.text.Element;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aNode;
import com.uchump.prime._PRIME._coPRIME.S_S.Nodex.eNode;

public class dNode extends eNode<eNode<Element>> implements Document, AttributeSet{

	//DOM DocumentNode lol, dEnum, descriptor-list
	//dEx,dEf,dEnum, Bag
	
	public ArrayList<eNode> Content = new ArrayList<eNode>();
	
	
	public dNode()
	{
		super();
	}
	
	public dNode(String name)
	{
		super(name);
	}
	
	public dNode(eNode<Element> Object) {
		super(Object);
		
	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addDocumentListener(DocumentListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeDocumentListener(DocumentListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUndoableEditListener(UndoableEditListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUndoableEditListener(UndoableEditListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getProperty(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putProperty(Object key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int offs, int len) throws BadLocationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getText(int offset, int length) throws BadLocationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getText(int offset, int length, Segment txt) throws BadLocationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Position getStartPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position getEndPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position createPosition(int offs) throws BadLocationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element[] getRootElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element getDefaultRootElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void render(Runnable r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAttributeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isDefined(Object attrName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEqual(AttributeSet attr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AttributeSet copyAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAttribute(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<?> getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsAttribute(Object name, Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAttributes(AttributeSet attributes) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AttributeSet getResolveParent() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	

	
	
	
}
