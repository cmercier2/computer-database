package test.excilys.argumenthandler;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import com.excilys.model.Computer;
import com.excilys.service.ArgumentHandler;

class ArgumentHandlerTest {

	@Test
	void creationArgument() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date d = sdf.parse("21/02/2018");
		java.util.Date dd = sdf.parse("21/12/2018");
		Date i = new Date(d.getTime());
		Date ii = new Date(dd.getTime());
		//Computer comp = new Computer("test", i, ii, 1);
		//assertEquals(comp, ArgumentHandler.creationArgument("createcomputer test;21/02/2018;21/12/2018;1"));
	}
	
	@Test
	void showArgument() throws ParseException {
		//assertEquals(new Computer(4), ArgumentHandler.showArgument("createcomputer 4"));
	}
	
	@Test
	void deleteArgument() throws ParseException {
		//assertEquals(new Computer(4), ArgumentHandler.deleteArgument("createcomputer 4"));
	}
	
	@Test
	void updateArgument() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date d = sdf.parse("21/02/2018");
		java.util.Date dd = sdf.parse("21/12/2018");
		Date i = new Date(d.getTime());
		Date ii = new Date(dd.getTime());
		//Computer comp = new Computer(1,"test", i, ii, 1);
		//assertEquals(comp, ArgumentHandler.creationArgument("createcomputer 1;test;21/02/2018;21/12/2018;1"));
	}

}
