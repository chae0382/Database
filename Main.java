package db_midproject;

import java.sql.*;

public class Main {
	public static void main(String[] args) throws Exception {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
			e.printStackTrace();
			return;
		}
		System.out.println("PostgreSQL JDBC Driver Registered!");
		/// if you have a error in this part, check jdbc driver(.jar file)

		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/project_movie", "postgres",
					"cse3207");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		/// if you have a error in this part, check DB information (db_name, user name,
		/// password)

		if (connection != null) {
			System.out.println(connection);
			System.out.println("연결 성공");
		} else {
			System.out.println("Failed to make connection!");
		}

		// Queries 1
		PreparedStatement pStmt;
		pStmt = connection.prepareStatement("create table director(" + "directorID int, " + "directorName varchar(20), "
				+ "dateOfBirth char(10), " + "dateOfDeath char(10), " + "primary key(directorID))");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("create table actor(" + "actorID int, " + "actorName varchar(20), "
				+ "dateOfBirth char(10), " + "dateOfDeath char(10), " + "gender varchar(6), "
				+ "check(gender in ('Male', 'Female')), " + "primary key(actorID))");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("create table movie (" + "movieID int, " + "movieName varchar(20), "
				+ "releaseYear char(4)," + "releaseMonth char(2), " + "releaseDate char(2), "
				+ "publisherName varchar(30), " + "avgRate numeric(3,2), " + "primary key(movieID))");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement(
				"create table award(" + "awardID int, " + "awardName varchar(30), " + "primary key(awardID))");
		pStmt.executeUpdate();
		pStmt = connection
				.prepareStatement("create table genre(" + "genreName varchar(10)," + "primary key(genreName))");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("create table movieGenre(" + "movieID int, " + "genreName varchar(10), "
				+ "primary key(movieID, genreName), " + "foreign key(movieID) references movie on delete cascade,"
				+ "foreign key(genreName) references genre)");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("create table movieObtain(" + "movieID int, " + "awardID int, "
				+ "year char(4), " + "primary key(movieID, awardID), "
				+ "foreign key(movieID) references movie on delete cascade, "
				+ "foreign key(awardID) references award)");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("create table actorObtain(" + "actorID int, " + "awardID int, "
				+ "year char(4), " + "primary key(actorID, awardID), " + "foreign key(actorID) references actor, "
				+ "foreign key(awardID) references award)");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("create table directorObtain(" + "directorID int, " + "awardID int, "
				+ "year char(4), " + "primary key(directorID, awardID), "
				+ "foreign key(directorID) references director, " + "foreign key(awardID) references award)");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("create table casting(" + "movieID int, " + "actorID int, "
				+ "role varchar(20), " + "primary key(movieID, actorID), "
				+ "foreign key(movieID) references movie on delete cascade, "
				+ "foreign key(actorID) references actor, " + "check(role in ('Main actor', 'Supporting Actor')))");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("create table make(" + "movieID int, " + "directorID int, "
				+ "primary key(movieID, directorID), " + "foreign key(movieID) references movie on delete cascade, "
				+ "foreign key(directorID) references director)");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement(
				"create table customer(" + "customerID int, " + "customerName varchar(20), " + "dateOfBirth char(10), "
						+ "gender varchar(6), " + "primary key(customerID), " + "check(gender in ('Male', 'Female')))");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("create table customerRate(" + "customerID int, " + "movieID int, "
				+ "rate numeric(3,2), " + "primary key(customerID, movieID),"
				+ "foreign key(customerID) references customer on delete cascade, "
				+ "foreign key(movieID) references movie on delete cascade)");
		pStmt.executeUpdate();
		System.out.println("Table created!");

		// Queries 1번
		pStmt = connection.prepareStatement("insert into director values(?,?,?,?)");
		pStmt.setInt(1, 1);
		pStmt.setString(2, "Tim Burton");
		pStmt.setString(3, "1958.08.25");
		pStmt.setString(4, null);
		pStmt.executeUpdate();

		pStmt.setInt(1, 2);
		pStmt.setString(2, "David Fincher");
		pStmt.setString(3, "1962.08.28");
		pStmt.setString(4, null);
		pStmt.executeUpdate();

		pStmt.setInt(1, 3);
		pStmt.setString(2, "Christopher Nolan");
		pStmt.setString(3, "1970.07.30");
		pStmt.setString(4, null);
		pStmt.executeUpdate();

		pStmt = connection.prepareStatement("insert into actor values(?,?,?,?,?)");
		pStmt.setInt(1, 1);
		pStmt.setString(2, "Johnny Depp");
		pStmt.setString(3, "1963.06.09");
		pStmt.setString(4, null);
		pStmt.setString(5, "Male");
		pStmt.executeUpdate();

		pStmt.setInt(1, 2);
		pStmt.setString(2, "Winona Ryder");
		pStmt.setString(3, "1971.10.29");
		pStmt.setString(4, null);
		pStmt.setString(5, "Female");
		pStmt.executeUpdate();

		pStmt.setInt(1, 3);
		pStmt.setString(2, "Mia Wasikowska");
		pStmt.setString(3, "1989.10.14");
		pStmt.setString(4, null);
		pStmt.setString(5, "Female");
		pStmt.executeUpdate();

		pStmt.setInt(1, 4);
		pStmt.setString(2, "Christian Bale");
		pStmt.setString(3, "1974.01.30");
		pStmt.setString(4, null);
		pStmt.setString(5, "Male");
		pStmt.executeUpdate();

		pStmt.setInt(1, 5);
		pStmt.setString(2, "Heath Ledger");
		pStmt.setString(3, "1979.04.04");
		pStmt.setString(4, "2008.01.22");
		pStmt.setString(5, "Male");
		pStmt.executeUpdate();

		pStmt.setInt(1, 6);
		pStmt.setString(2, "Jesse Eisenberg");
		pStmt.setString(3, "1983.10.05");
		pStmt.setString(4, null);
		pStmt.setString(5, "Male");
		pStmt.executeUpdate();

		pStmt.setInt(1, 7);
		pStmt.setString(2, "Justin Timberlake");
		pStmt.setString(3, "1981.01.31");
		pStmt.setString(4, null);
		pStmt.setString(5, "Male");
		pStmt.executeUpdate();

		pStmt.setInt(1, 8);
		pStmt.setString(2, "Fionn Whitehead");
		pStmt.setString(3, "1997.07.18");
		pStmt.setString(4, null);
		pStmt.setString(5, "Male");
		pStmt.executeUpdate();

		pStmt.setInt(1, 9);
		pStmt.setString(2, "Tom Hardy");
		pStmt.setString(3, "1977.09.15");
		pStmt.setString(4, null);
		pStmt.setString(5, "Male");
		pStmt.executeUpdate();

		pStmt = connection.prepareStatement("insert into movie values(?,?,?,?,?,?,?)");
		pStmt.setInt(1, 1);
		pStmt.setString(2, "Edward Scissorhands");
		pStmt.setString(3, "1991");
		pStmt.setString(4, "06");
		pStmt.setString(5, "29");
		pStmt.setString(6, "20th Century Fox Presents");
		pStmt.setInt(7, 0);
		pStmt.executeUpdate();

		pStmt.setInt(1, 2);
		pStmt.setString(2, "Alice In Wonderland");
		pStmt.setString(3, "2010");
		pStmt.setString(4, "03");
		pStmt.setString(5, "04");
		pStmt.setString(6, "Korea Sony Pictures");
		pStmt.setInt(7, 0);
		pStmt.executeUpdate();

		pStmt.setInt(1, 3);
		pStmt.setString(2, "The Social Network");
		pStmt.setString(3, "2010");
		pStmt.setString(4, "11");
		pStmt.setString(5, "18");
		pStmt.setString(6, "Korea Sony Pictures");
		pStmt.setInt(7, 0);
		pStmt.executeUpdate();

		pStmt.setInt(1, 4);
		pStmt.setString(2, "The Dark Knight");
		pStmt.setString(3, "2008");
		pStmt.setString(4, "08");
		pStmt.setString(5, "06");
		pStmt.setString(6, "Warner Brothers Korea");
		pStmt.setInt(7, 0);
		pStmt.executeUpdate();

		pStmt.setInt(1, 5);
		pStmt.setString(2, "Dunkirk");
		pStmt.setString(3, "17");
		pStmt.setString(4, "07");
		pStmt.setString(5, "12");
		pStmt.setString(6, "Warner Brothers Korea");
		pStmt.setInt(7, 0);
		pStmt.executeUpdate();

		pStmt = connection.prepareStatement("insert into customer values(?,?,?,?)");
		pStmt.setInt(1, 1);
		pStmt.setString(2, "Ethan");
		pStmt.setString(3, "1997.11.14");
		pStmt.setString(4, "Male");
		pStmt.executeUpdate();

		pStmt.setInt(1, 2);
		pStmt.setString(2, "John");
		pStmt.setString(3, "1978.01.23");
		pStmt.setString(4, "Male");
		pStmt.executeUpdate();

		pStmt.setInt(1, 3);
		pStmt.setString(2, "Hayden");
		pStmt.setString(3, "1980.05.04");
		pStmt.setString(4, "Female");
		pStmt.executeUpdate();

		pStmt.setInt(1, 4);
		pStmt.setString(2, "Jill");
		pStmt.setString(3, "1981.04.17");
		pStmt.setString(4, "Female");
		pStmt.executeUpdate();

		pStmt.setInt(1, 5);
		pStmt.setString(2, "Bell");
		pStmt.setString(3, "1990.05.14");
		pStmt.setString(4, "Female");
		pStmt.executeUpdate();

		pStmt = connection.prepareStatement("insert into genre values(?)");
		pStmt.setString(1, "Fantasy");
		pStmt.executeUpdate();

		pStmt.setString(1, "Romance");
		pStmt.executeUpdate();

		pStmt.setString(1, "Adventure");
		pStmt.executeUpdate();

		pStmt.setString(1, "Family");
		pStmt.executeUpdate();

		pStmt.setString(1, "Drama");
		pStmt.executeUpdate();

		pStmt.setString(1, "Mystery");
		pStmt.executeUpdate();

		pStmt.setString(1, "Thriller");
		pStmt.executeUpdate();

		pStmt.setString(1, "War");
		pStmt.executeUpdate();

		pStmt.setString(1, "Action");
		pStmt.executeUpdate();

		pStmt = connection.prepareStatement("insert into movieGenre values(?,?)");
		pStmt.setInt(1, 1);
		pStmt.setString(2, "Fantasy");
		pStmt.executeUpdate();

		pStmt.setInt(1, 1);
		pStmt.setString(2, "Romance");
		pStmt.executeUpdate();

		pStmt.setInt(1, 2);
		pStmt.setString(2, "Fantasy");
		pStmt.executeUpdate();

		pStmt.setInt(1, 2);
		pStmt.setString(2, "Adventure");
		pStmt.executeUpdate();

		pStmt.setInt(1, 2);
		pStmt.setString(2, "Family");
		pStmt.executeUpdate();

		pStmt.setInt(1, 3);
		pStmt.setString(2, "Drama");
		pStmt.executeUpdate();

		pStmt.setInt(1, 4);
		pStmt.setString(2, "Action");
		pStmt.executeUpdate();

		pStmt.setInt(1, 4);
		pStmt.setString(2, "Drama");
		pStmt.executeUpdate();

		pStmt.setInt(1, 4);
		pStmt.setString(2, "Mystery");
		pStmt.executeUpdate();

		pStmt.setInt(1, 4);
		pStmt.setString(2, "Thriller");
		pStmt.executeUpdate();

		pStmt.setInt(1, 5);
		pStmt.setString(2, "Action");
		pStmt.executeUpdate();

		pStmt.setInt(1, 5);
		pStmt.setString(2, "Drama");
		pStmt.executeUpdate();

		pStmt.setInt(1, 5);
		pStmt.setString(2, "Thriller");
		pStmt.executeUpdate();

		pStmt.setInt(1, 5);
		pStmt.setString(2, "War");
		pStmt.executeUpdate();

		pStmt = connection.prepareStatement("insert into casting values(?,?,?)");
		pStmt.setInt(1, 1);
		pStmt.setInt(2, 1);
		pStmt.setString(3, "Main actor");
		pStmt.executeUpdate();

		pStmt.setInt(1, 1);
		pStmt.setInt(2, 2);
		pStmt.setString(3, "Main actor");
		pStmt.executeUpdate();

		pStmt.setInt(1, 2);
		pStmt.setInt(2, 1);
		pStmt.setString(3, "Main actor");
		pStmt.executeUpdate();

		pStmt.setInt(1, 2);
		pStmt.setInt(2, 3);
		pStmt.setString(3, "Main actor");
		pStmt.executeUpdate();

		pStmt.setInt(1, 3);
		pStmt.setInt(2, 6);
		pStmt.setString(3, "Main actor");
		pStmt.executeUpdate();

		pStmt.setInt(1, 3);
		pStmt.setInt(2, 7);
		pStmt.setString(3, "Supporting Actor");
		pStmt.executeUpdate();

		pStmt.setInt(1, 4);
		pStmt.setInt(2, 4);
		pStmt.setString(3, "Main actor");
		pStmt.executeUpdate();

		pStmt.setInt(1, 4);
		pStmt.setInt(2, 5);
		pStmt.setString(3, "Main actor");
		pStmt.executeUpdate();

		pStmt.setInt(1, 5);
		pStmt.setInt(2, 8);
		pStmt.setString(3, "Main actor");
		pStmt.executeUpdate();

		pStmt.setInt(1, 5);
		pStmt.setInt(2, 9);
		pStmt.setString(3, "Supporting Actor");
		pStmt.executeUpdate();

		pStmt = connection.prepareStatement("insert into make values(?,?)");
		pStmt.setInt(1, 1);
		pStmt.setInt(2, 1);
		pStmt.executeUpdate();

		pStmt.setInt(1, 2);
		pStmt.setInt(2, 1);
		pStmt.executeUpdate();

		pStmt.setInt(1, 3);
		pStmt.setInt(2, 2);
		pStmt.executeUpdate();

		pStmt.setInt(1, 4);
		pStmt.setInt(2, 3);
		pStmt.executeUpdate();

		pStmt.setInt(1, 5);
		pStmt.setInt(2, 3);
		pStmt.executeUpdate();

		System.out.println("Initial data inserted!");

		Statement stmt = connection.createStatement();

		// Queries 2.1
		System.out.println();
		System.out.println("Statement : Winona Ryder won the “Best supporting actor” award in 1994");
		System.out.println("Translated SQL : select actorID from actor where actorName='Winona Ryder'");
		System.out.println("Translated SQL : insert into award values (1, 'Best supporting actor'");
		System.out.println("Translated SQL : select awardID from award where awardName='Best supporting actor");
		System.out.println("Translated SQL : insert into actorObtain values (2, 1, 1994)");
		pStmt = connection.prepareStatement("insert into award values (1, 'Best supporting actor')");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("insert into actorObtain values (2, 1, 1994)");
		pStmt.executeUpdate();
		ResultSet rs = stmt.executeQuery("select * from award");
		ResultSetMetaData rsmd = rs.getMetaData();
		System.out.println("\n-----<award>-----\n");
		System.out.printf("%30s%30s\n", "awardID", "awardName");
		while (rs.next()) {
			System.out.printf("%30d%30s\n", rs.getInt(1), rs.getString(2));
		}
		rs = stmt.executeQuery("select * from actorObtain");
		System.out.println("\n-----<actorObtain>-----\n");
		System.out.printf("%30s%30s%30s\n", "actorID", "awardID", "year");
		while (rs.next()) {
			System.out.printf("%30d%30d%30s\n", rs.getInt(1), rs.getInt(2), rs.getString(3));
		}

		// Queries 2.2
		System.out.println();
		System.out.println("Statement : Tom Hardy won the “Best supporting actor” award in 2018.");
		System.out.println("Translated SQL : select actorID from actor where actorName='Tom Hardy'");
		System.out.println("Translated SQL : select awardID from award where awardName='Best supporting actor");
		System.out.println("Translated SQL : insert into actorObtain values (9, 1, 2018)");
		pStmt = connection.prepareStatement("insert into actorObtain values (9, 1, 2018)");
		pStmt.executeUpdate();
		rs = stmt.executeQuery("select * from actorObtain");
		System.out.println("\n-----<actorObtain>-----\n");
		System.out.printf("%30s%30s%30s\n", "actorID", "awardID", "year");
		while (rs.next()) {
			System.out.printf("%30d%30d%30s\n", rs.getInt(1), rs.getInt(2), rs.getString(3));
		}

		// Queries 2.3
		System.out.println();
		System.out.println("Statement : Heath Ledger won the “Best villain actor” award in 2009.");
		System.out.println("Translated SQL : select actorID from actor where actorName='Heath Ledger'");
		System.out.println("Translated SQL : insert into award values (2, 'Best villain actor')");
		System.out.println("Translated SQL : select awardID from award where awardName='Best villain actor'");
		System.out.println("Translated SQL : insert into actorObtain values (5, 2, 2009)");
		pStmt = connection.prepareStatement("insert into award values (2, 'Best villain actor')");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("insert into actorObtain values (5, 2, 2009)");
		pStmt.executeUpdate();
		rs = stmt.executeQuery("select * from award");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<award>-----\n");
		System.out.printf("%30s%30s\n", "awardID", "awardName");
		while (rs.next()) {
			System.out.printf("%30d%30s\n", rs.getInt(1), rs.getString(2));
		}
		rs = stmt.executeQuery("select * from actorObtain");
		System.out.println("\n-----<actorObtain>-----\n");
		System.out.printf("%30s%30s%30s\n", "actorID", "awardID", "year");
		while (rs.next()) {
			System.out.printf("%30d%30d%30s\n", rs.getInt(1), rs.getInt(2), rs.getString(3));
		}

		// Queries 2.4
		System.out.println();
		System.out.println("Statement : Johnny Depp won the “Best main actor” award in 2011.");
		System.out.println("Translated SQL : select actorID from actor where actorName='Johnny Depp'");
		System.out.println("Translated SQL : insert into award values (3, 'Best main actor')");
		System.out.println("Translated SQL : select awardID from award where awardName='Best main actor'");
		System.out.println("Translated SQL : insert into actorObtain values (1, 3, 2011)");
		pStmt = connection.prepareStatement("insert into award values (3, 'Best main actor')");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("insert into actorObtain values (1, 3, 2011)");
		pStmt.executeUpdate();
		rs = stmt.executeQuery("select * from award");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<award>-----\n");
		System.out.printf("%30s%30s\n", "awardID", "awardName");
		while (rs.next()) {
			System.out.printf("%30d%30s\n", rs.getInt(1), rs.getString(2));
		}
		rs = stmt.executeQuery("select * from actorObtain");
		System.out.println("\n-----<actorObtain>-----\n");
		System.out.printf("%30s%30s%30s\n", "actorID", "awardID", "year");
		while (rs.next()) {
			System.out.printf("%30d%30d%30s\n", rs.getInt(1), rs.getInt(2), rs.getString(3));
		}

		// Queries 2.5
		System.out.println();
		System.out.println("Statement : Edward Scissorhands  won the “Best fantasy movie” award in 1991.");
		System.out.println("Translated SQL : select movieID from movie where movieName='Edward Scissorhands'");
		System.out.println("Translated SQL : insert into award values (4, 'Best fantasy movie')");
		System.out.println("Translated SQL : select awardID from award where awardName='Best fantasy movie'");
		System.out.println("Translated SQL : insert into movieObtain values (1, 4, 1991)");
		pStmt = connection.prepareStatement("insert into award values (4, 'Best fantasy movie')");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("insert into movieObtain values (1, 4, 1991)");
		pStmt.executeUpdate();
		rs = stmt.executeQuery("select * from award");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<award>-----\n");
		System.out.printf("%30s%30s\n", "awardID", "awardName");
		while (rs.next()) {
			System.out.printf("%30d%30s\n", rs.getInt(1), rs.getString(2));
		}
		rs = stmt.executeQuery("select * from movieObtain");
		System.out.println("\n-----<movieObtain>-----\n");
		System.out.printf("%30s%30s%30s\n", "movieID", "awardID", "year");
		while (rs.next()) {
			System.out.printf("%30d%30d%30s\n", rs.getInt(1), rs.getInt(2), rs.getString(3));
		}

		// Queries 2.6
		System.out.println();
		System.out.println("Statement : Alice In Wonderland won the “Best fantasy movie” award in 2011");
		System.out.println("Translated SQL : select movieID from movie where movieName='Alice In Wonderland'");
		System.out.println("Translated SQL : select awardID from award where awardName='Best fantasy movie'");
		System.out.println("Translated SQL : insert into movieObtain values (2, 4, 2011)");
		pStmt = connection.prepareStatement("insert into movieObtain values (2, 4, 2011)");
		pStmt.executeUpdate();
		System.out.println("\n-----<movieObtain>-----\n");
		System.out.printf("%30s%30s%30s\n", "movieID", "awardID", "year");
		while (rs.next()) {
			System.out.printf("%30d%30d%30s\n", rs.getInt(1), rs.getInt(2), rs.getString(3));
		}

		// Queries 2.7
		System.out.println();
		System.out.println("Statement : The Dark Knight won the “Best picture” award in 2009");
		System.out.println("Translated SQL : select movieID from movie where movieName='The Dark Knight'");
		System.out.println("Translated SQL : insert into award values (5, 'Best picture')");
		System.out.println("Translated SQL : select awardID from award where awardName='Best picture'");
		System.out.println("Translated SQL : insert into movieObtain values (4, 5, 2009)");
		pStmt = connection.prepareStatement("insert into award values (5, 'Best picture')");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("insert into movieObtain values (4, 5, 2009)");
		pStmt.executeUpdate();
		rs = stmt.executeQuery("select * from award");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<award>-----\n");
		System.out.printf("%30s%30s\n", "awardID", "awardName");
		while (rs.next()) {
			System.out.printf("%30d%30s\n", rs.getInt(1), rs.getString(2));
		}
		rs = stmt.executeQuery("select * from movieObtain");
		System.out.println("\n-----<movieObtain>-----\n");
		System.out.printf("%30s%30s%30s\n", "movieID", "awardID", "year");
		while (rs.next()) {
			System.out.printf("%30d%30d%30s\n", rs.getInt(1), rs.getInt(2), rs.getString(3));
		}

		// Queries 2.8
		System.out.println();
		System.out.println("Statement : Christopher Nolan won the “Best director” award in 2018");
		System.out.println("Translated SQL : select directorID from director where directorName='Christopher Nolan'");
		System.out.println("Translated SQL : insert into award values (6, 'Best director')");
		System.out.println("Translated SQL : select awardID from award where awardName='Best director'");
		System.out.println("Translated SQL : insert into directorObtain values (3, 6, 2018)");
		pStmt = connection.prepareStatement("insert into award values (6, 'Best director')");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("insert into directorObtain values (3, 6, 2018)");
		pStmt.executeUpdate();
		rs = stmt.executeQuery("select * from award");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<award>-----\n");
		System.out.printf("%30s%30s\n", "awardID", "awardName");
		while (rs.next()) {
			System.out.printf("%30d%30s\n", rs.getInt(1), rs.getString(2));
		}
		rs = stmt.executeQuery("select * from directorObtain");
		System.out.println("\n-----<directorObtain>-----\n");
		System.out.printf("%30s%30s%30s\n", "directorID", "awardID", "year");
		while (rs.next()) {
			System.out.printf("%30d%30d%30s\n", rs.getInt(1), rs.getInt(2), rs.getString(3));
		}

		// Queries 3.1
		System.out.println();
		System.out.println("Statement : Ethan rates 5 to “Dunkirk”.");
		System.out.println("Translated SQL : select customerID from customer where customerName='Ethan'");
		System.out.println("Translated SQL : select movieID from movie where movieName='Dunkirk'");
		System.out.println("Translated SQL : insert into customerRate values (1, 5, 5)");
		System.out.println("Translated SQL : update movie set avgRate = 5 where movieID= 5");
		pStmt = connection.prepareStatement("insert into customerRate values (1, 5, 5)");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("update movie set avgRate = 5 where movieID = 5");
		pStmt.executeUpdate();
		rs = stmt.executeQuery("select * from customerRate");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<customerRate>-----\n");
		System.out.printf("%30s%30s%30s\n", "customerID", "movieID", "rate");
		while (rs.next()) {
			System.out.printf("%30d%30d%30f\n", rs.getInt(1), rs.getInt(2), rs.getFloat(3));
		}
		rs = stmt.executeQuery("select movieID, movieName, avgRate from movie");
		System.out.println("\n-----<movie>-----\n");
		System.out.printf("%30s%30s%30s\n", "movieID", "movieName", "avgRate");
		while (rs.next()) {
			System.out.printf("%30d%30s%30f\n", rs.getInt(1), rs.getString(2), rs.getFloat(3));
		}

		// Queries 3.2
		System.out.println();
		System.out.println("Statement : Bell rates 5 to the movies whose director is “Tim Burton”.");
		System.out.println("Translated SQL : select customerID from customer where customerName='Bell'");
		System.out.println("Translated SQL : select directorID from director where directorName='Tim Burton'");
		System.out.println("Translated SQL : select movieID from make where directorID = 1");
		System.out.println("Translated SQL : insert into customerRate values (5, 1, 5)");
		System.out.println("Translated SQL : insert into customerRate values (5, 2, 5)");
		System.out.println("Translated SQL : update movie set avgRate = 5 where movieID= 1");
		System.out.println("Translated SQL : update movie set avgRate = 5 where movieID= 2");
		pStmt = connection.prepareStatement("insert into customerRate values (5, 1, 5)");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("insert into customerRate values (5, 2, 5)");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("update movie set avgRate = 5 where movieID = 1");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("update movie set avgRate = 5 where movieID = 2");
		pStmt.executeUpdate();
		rs = stmt.executeQuery("select * from customerRate");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<customerRate>-----\n");
		System.out.printf("%30s%30s%30s\n", "customerID", "movieID", "rate");
		while (rs.next()) {
			System.out.printf("%30d%30d%30.2f\n", rs.getInt(1), rs.getInt(2), rs.getFloat(3));
		}
		rs = stmt.executeQuery("select movieID, movieName, avgRate from movie order by movieID");
		System.out.println("\n-----<movie>-----\n");
		System.out.printf("%30s%30s%30s\n", "movieID", "movieName", "avgRate");
		while (rs.next()) {
			System.out.printf("%30d%30s%30.2f\n", rs.getInt(1), rs.getString(2), rs.getFloat(3));
		}

		// Queries 3.3
		System.out.println();
		System.out.println("Statement : Jill rates 4 to the movies whose main actor is female.");
		System.out.println("Translated SQL : select customerID from customer where customerName='Jill'");
		System.out.println("Translated SQL : select actorID from actor where gender='Female'");
		System.out.println(
				"Translated SQL : select movieID from casting where (actorID = 2 or actorID = 3) and role = 'Main actor'");
		System.out.println("Translated SQL : insert into customerRate values (4, 1, 4)");
		System.out.println("Translated SQL : insert into customerRate values (4, 2, 4)");
		System.out.println("Translated SQL : update movie set avgRate = 4.5 where movieID= 1");
		System.out.println("Translated SQL : update movie set avgRate = 4.5 where movieID= 2");
		pStmt = connection.prepareStatement("insert into customerRate values (4, 1, 4)");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("insert into customerRate values (4, 2, 4)");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("update movie set avgRate = 4.5 where movieID = 1");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("update movie set avgRate = 4.5 where movieID = 2");
		pStmt.executeUpdate();
		rs = stmt.executeQuery("select * from customerRate");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<customerRate>-----\n");
		System.out.printf("%30s%30s%30s\n", "customerID", "movieID", "rate");
		while (rs.next()) {
			System.out.printf("%30d%30d%30.2f\n", rs.getInt(1), rs.getInt(2), rs.getFloat(3));
		}
		rs = stmt.executeQuery("select movieID, movieName, avgRate from movie order by movieID");
		System.out.println("\n-----<movie>-----\n");
		System.out.printf("%30s%30s%30s\n", "movieID", "movieName", "avgRate");
		while (rs.next()) {
			System.out.printf("%30d%30s%30.2f\n", rs.getInt(1), rs.getString(2), rs.getFloat(3));
		}

		// Queries 3.4
		System.out.println();
		System.out.println("Statement : Hayden rates 4 to the fantasy movies.");
		System.out.println("Translated SQL : select customerID from customer where customerName='Hayden'");
		System.out.println("Translated SQL : select movieID from genreName where ='fantasy'");
		System.out.println("Translated SQL : select directorID from directorObtain where awardID = 6");
		System.out.println("Translated SQL : select movieID from make where directorID = 3");
		System.out.println("Translated SQL : insert into customerRate values (3, 1, 4)");
		System.out.println("Translated SQL : insert into customerRate values (3, 2, 4)");
		System.out.println("Translated SQL : update movie set avgRate = 4.25 where movieID= 1");
		System.out.println("Translated SQL : update movie set avgRate = 4.25 where movieID= 2");
		pStmt = connection.prepareStatement("insert into customerRate values (3, 1, 4)");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("insert into customerRate values (3, 2, 4)");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("update movie set avgRate = 4.25 where movieID = 1");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("update movie set avgRate = 4.25 where movieID = 2");
		pStmt.executeUpdate();
		rs = stmt.executeQuery("select * from customerRate");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<customerRate>-----\n");
		System.out.printf("%30s%30s%30s\n", "customerID", "movieID", "rate");
		while (rs.next()) {
			System.out.printf("%30d%30d%30.2f\n", rs.getInt(1), rs.getInt(2), rs.getFloat(3));
		}
		rs = stmt.executeQuery("select movieID, movieName, avgRate from movie order by movieID");
		System.out.println("\n-----<movie>-----\n");
		System.out.printf("%30s%30s%30s\n", "movieID", "movieName", "avgRate");
		while (rs.next()) {
			System.out.printf("%30d%30s%30.2f\n", rs.getInt(1), rs.getString(2), rs.getFloat(3));
		}

		// Queries 3.5
		System.out.println();
		System.out.println("Statement : John rates 5 to the movies whose director won the “Best director” award.");
		System.out.println("Translated SQL : select customerID from customer where customerName='John'");
		System.out.println("Translated SQL : select awardID from award where awardName ='Best director'");
		System.out.println("Translated SQL : select directorID from directorObtain where awardID = 6");
		System.out.println("Translated SQL : select movieID from make where directorID = 3");
		System.out.println("Translated SQL : insert into customerRate values (2, 4, 5)");
		System.out.println("Translated SQL : insert into customerRate values (2, 5, 5)");
		System.out.println("Translated SQL : update movie set avgRate = 5 where movieID= 4");
		pStmt = connection.prepareStatement("insert into customerRate values (2, 4, 5)");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("insert into customerRate values (2, 5, 5)");
		pStmt.executeUpdate();
		pStmt = connection.prepareStatement("update movie set avgRate = 5 where movieID = 4");
		pStmt.executeUpdate();
		rs = stmt.executeQuery("select * from customerRate");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<customerRate>-----\n");
		System.out.printf("%30s%30s%30s\n", "customerID", "movieID", "rate");
		while (rs.next()) {
			System.out.printf("%30d%30d%30.2f\n", rs.getInt(1), rs.getInt(2), rs.getFloat(3));
		}
		rs = stmt.executeQuery("select movieID, movieName, avgRate from movie order by movieID");
		System.out.println("\n-----<movie>-----\n");
		System.out.printf("%30s%30s%30s\n", "movieID", "movieName", "avgRate");
		while (rs.next()) {
			System.out.printf("%30d%30s%30.2f\n", rs.getInt(1), rs.getString(2), rs.getFloat(3));
		}

		// Queries 4
		System.out.println();
		System.out.println("Statement : Select the names of the movies whose actor are dead.");
		System.out.println(
				"Translated SQL : select movieName from movie where movieID in (select movieID from casting natural join actor where dateOfDeath is not null)");
		rs = stmt.executeQuery(
				"select movieName from movie where movieID in (select movieID from casting natural join actor where dateOfDeath is not null)");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<Query 4 result table>-----\n");
		System.out.printf("%30s\n", "movieName");
		while (rs.next()) {
			System.out.printf("%30s\n", rs.getString(1));
		}
		// Queries 5
		System.out.println();
		System.out.println("Statement : Select the names of the directors who cast the same actor more than once.");
		System.out.println(
				"Translated SQL : select directorName from director where directorID in (select directorID from casting natural join make as S ");
		System.out.println("where (actorID, directorID) = some(select actorID, directorID from casting natural join make as T where T.movieID <> S.movieID))");
		rs = stmt.executeQuery(
				"select directorName from director where directorID in (select directorID from casting natural join make as S where (actorID, directorID) = some(select actorID, directorID from casting natural join make as T where T.movieID <> S.movieID))");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<Query 5 result table>-----\n");
		System.out.printf("%30s\n", "directorName");
		while (rs.next()) {
			System.out.printf("%30s\n", rs.getString(1));
		}
		// Queries 6
		System.out.println();
		System.out.println(
				"Statement : Select the names of the movies and the genres, where movies have the common genre.");
		System.out.println(
				"Translated SQL : select movieName, genreName from movie natural join movieGenre as S ");
		System.out.println("where movieName = some(select movieName from movie natural join movieGenre as T where S.genreName <> T.genreName)");
		rs = stmt.executeQuery(
				"select movieName, genreName from movie natural join movieGenre as S where movieName = some(select movieName from movie natural join movieGenre as T where S.genreName <> T.genreName)");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<Query 6 result table>-----\n");
		System.out.printf("%30s%30s\n", "movieName", "genreName");
		while (rs.next()) {
			System.out.printf("%30s%30s\n", rs.getString(1), rs.getString(2));
		}
		// Queries 7
		System.out.println();
		System.out.println(
				"Statement : Delete the movies whose director or actor did not get any award and delete data from related tables.");
		System.out.println(
				"Translated SQL : delete from movie where movieID in(select distinct movieID from (make natural join casting natural left outer join directorObtain)");
		System.out.println("left outer join actorObtain using(actorID) where directorObtain.awardID is null and actorObtain.awardID is null)");
		pStmt = connection.prepareStatement(
				"delete from movie where movieID in(select distinct movieID from (make natural join casting natural left outer join directorObtain)left outer join actorObtain using(actorID) where directorObtain.awardID is null and actorObtain.awardID is null)");
		pStmt.executeUpdate();
		rs = stmt.executeQuery("select * from movie order by movieID");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<movie>-----\n");
		System.out.printf("%30s%30s%30s%30s%30s%30s%30s\n", "movieID", "movieName", "releaseYear", "releaseMonth",
				"releaseDate", "publisherName", "avgRate");
		while (rs.next()) {
			System.out.printf("%30d%30s%30s%30s%30s%30s%30.2f\n", rs.getInt(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getFloat(7));
		}
		rs = stmt.executeQuery("select * from movieGenre order by movieID");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<movieGenre>-----\n");
		System.out.printf("%30s%30s\n", "movieID", "genreName");
		while (rs.next()) {
			System.out.printf("%30d%30s\n", rs.getInt(1), rs.getString(2));
		}
		rs = stmt.executeQuery("select * from movieObtain order by movieID");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<movieObtain>-----\n");
		System.out.printf("%30s%30s%30s\n", "movieID", "awardID", "year");
		while (rs.next()) {
			System.out.printf("%30d%30d%30s\n", rs.getInt(1), rs.getInt(2), rs.getString(3));
		}
		rs = stmt.executeQuery("select * from casting order by movieID");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<casting>-----\n");
		System.out.printf("%30s%30s%30s\n", "movieID", "actorID", "role");
		while (rs.next()) {
			System.out.printf("%30d%30d%30s\n", rs.getInt(1), rs.getInt(2), rs.getString(3));
		}
		rs = stmt.executeQuery("select * from make order by movieID");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<make>-----\n");
		System.out.printf("%30s%30s\n", "movieID", "directorID");
		while (rs.next()) {
			System.out.printf("%30d%30d\n", rs.getInt(1), rs.getInt(2));
		}
		rs = stmt.executeQuery("select * from customerRate order by movieID");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<customerRate>-----\n");
		System.out.printf("%30s%30s%30s\n", "customerID", "movieID", "rate");
		while (rs.next()) {
			System.out.printf("%30d%30d%30.3f\n", rs.getInt(1), rs.getInt(2), rs.getFloat(3));
		}

		// Queries 8
		System.out.println();
		System.out.println("Statement : Delete all customers and delete data from related tables.");
		System.out.println("Translated SQL : delete from customer");
		pStmt = connection.prepareStatement("delete from customer");
		pStmt.executeUpdate();
		rs = stmt.executeQuery("select * from customer");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<customer>-----\n");
		System.out.printf("%30s%30s%30s%30s\n", "customerID", "customerName", "dateOfBirth", "gender");
		while (rs.next()) {
			System.out.printf("%30d%30s%30s%30s\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
		}
		rs = stmt.executeQuery("select * from customerRate");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<customerRate>-----\n");
		System.out.printf("%30s%30s%30s\n", "customerID", "movieID", "rate");
		while (rs.next()) {
			System.out.printf("%30d%30d%30.3f\n", rs.getInt(1), rs.getInt(2), rs.getFloat(3));
		}
		// Queries 9
		System.out.println();
		System.out.println("Statement : Delete all tables and data (make the database empty).");
		System.out.println(
				"Translated SQL : drop table customerRate, customer, movieGenre, movieObtain, actorObtain, directorObtain, casting, make, director, actor, movie, award, genre");
		pStmt = connection.prepareStatement(
				"drop table customerRate, customer, movieGenre, movieObtain, actorObtain, directorObtain, casting, make, director, actor, movie, award, genre");
		pStmt.executeUpdate();
		rs = stmt.executeQuery("SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'project_movie';");
		rsmd = rs.getMetaData();
		System.out.println("\n-----<table 수>-----\n");
		System.out.printf("%30s\n", "Number of Table");
		while (rs.next()) {
			System.out.printf("%30d\n", rs.getInt(1));
		}

		connection.close();
	}
}