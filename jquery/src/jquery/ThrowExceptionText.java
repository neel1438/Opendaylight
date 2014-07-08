package jquery;

import org.w3c.dom.DOMException;
import org.w3c.dom.Text;


public class ThrowExceptionText extends ThrowExceptionNode {


	public String getData() throws DOMException {
		throw new RuntimeException( "Not Implemented" );
	}


	public void setData(String data) throws DOMException {
		throw new RuntimeException( "Not Implemented" );
	}


	public int getLength() {
		throw new RuntimeException( "Not Implemented" );
	}

	public String substringData(int offset, int count) throws DOMException {
		throw new RuntimeException( "Not Implemented" );
	}

	public void appendData(String arg) throws DOMException {
		throw new RuntimeException( "Not Implemented" );
		
	}

	public void insertData(int offset, String arg) throws DOMException {
		throw new RuntimeException( "Not Implemented" );
		
	}

	public void deleteData(int offset, int count) throws DOMException {
		throw new RuntimeException( "Not Implemented" );
		
	}


	public void replaceData(int offset, int count, String arg)
			throws DOMException {
		throw new RuntimeException( "Not Implemented" );
		
	}


	public Text splitText(int offset) throws DOMException {
		throw new RuntimeException( "Not Implemented" );
	}


	public boolean isElementContentWhitespace() {
		throw new RuntimeException( "Not Implemented" );
	}


	public String getWholeText() {
		throw new RuntimeException( "Not Implemented" );
	}


	public Text replaceWholeText(String content) throws DOMException {
		throw new RuntimeException( "Not Implemented" );
	}

}
