package visitcounter.receiver;


import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;



public class FootfallModelTest {

	@Test
	public void whenFootFallModelCreatedThenThrowNoException() {
		
		LocalDate expectedDate = LocalDate.now();
		LocalTime expectedTime = LocalTime.now();
		FootfallModel model = new FootfallModel(expectedTime, expectedDate);
		
		Assert.assertEquals(expectedDate, model.getDate());
		Assert.assertEquals(expectedTime, model.getTime());		
	}

}
