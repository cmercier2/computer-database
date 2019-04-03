package com.excilys.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.excilys.model.Computer;
import com.excilys.model.Computer.ComputerBuilder;

public class MapResultSetTest {

	@Mock
	private ResultSet res = Mockito.mock(ResultSet.class);

	@BeforeEach
	public void mockFunction() throws SQLException {
		System.out.println("EXCECUTE");
		mockNext();
		mockGetId();
		mockGetName();
		mockGetIntroduced();
		mockGetDiscontinued();
		mockGetCompanyId();
	}

	private void mockNext() throws SQLException {
		System.out.println("testNEXT");
		Mockito.when(res.next()).thenReturn(true).thenReturn(true).thenReturn(false);
	}

	private void mockGetId() throws SQLException {
		System.out.println("testID");
		Mockito.when(res.getInt("id")).thenReturn(new Integer(1)).thenReturn(new Integer(2));
	}

	private void mockGetName() throws SQLException {
		System.out.println("testNAME");
		Mockito.when(res.getString("name")).thenReturn("test").thenReturn("test2");
	}

	private void mockGetIntroduced() throws SQLException {
		System.out.println("testINTRODUCED");
		Mockito.when(res.getDate("introduced")).thenReturn(new Date(new Timestamp(10000).getTime()))
				.thenReturn(new Date(new Timestamp(11000).getTime()));
	}

	private void mockGetDiscontinued() throws SQLException {
		System.out.println("testDISCONTINUED");
		Mockito.when(res.getDate("discontinued")).thenReturn(new Date(new Timestamp(15000).getTime()))
				.thenReturn(new Date(new Timestamp(16000).getTime()));
	}

	private void mockGetCompanyId() throws SQLException {
		System.out.println("testCOMPANY");
		Mockito.when(res.getInt("company_id")).thenReturn(new Integer(8)).thenReturn(new Integer(9));
	}

	@Test
	public void MapComputerTest() throws SQLException {
		Optional<Computer> optComputer = MapResultSet.mapResultSetComputer(res);
		assertTrue(optComputer.isPresent());
		Computer computer = optComputer.get();
		assertEquals(new Integer(1), computer.getId());
		assertEquals(new Integer(1), computer.getId());
		assertEquals(new Date(new Timestamp(10000).getTime()), computer.getIntroduced());
		assertEquals(new Date(new Timestamp(15000).getTime()), computer.getDiscontinued());
		assertEquals(new Integer(8), computer.getCompany());
	}

	@Test
	public void MapAllComputerTest() throws SQLException {
		List<Computer> allComputer = new ArrayList<>();
		List<Computer> allComputerRes = MapResultSet.mapAllResultSetComputer(res);
		allComputer.add(new ComputerBuilder().setId(new Integer(1)).setName("test")
				.setIntroduced(new Date(new Timestamp(10000).getTime()))
				.setDiscontinued(new Date(new Timestamp(15000).getTime())).setCompany(new Integer(8)).build());
		allComputer.add(new ComputerBuilder().setId(new Integer(2)).setName("test2")
				.setIntroduced(new Date(new Timestamp(11000).getTime()))
				.setDiscontinued(new Date(new Timestamp(16000).getTime())).setCompany(new Integer(9)).build());
		assertEquals(allComputer, allComputerRes);
	}

}
