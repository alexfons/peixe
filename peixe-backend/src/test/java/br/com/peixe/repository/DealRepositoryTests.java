
package br.com.peixe.repository;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DealRepositoryTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private DealRepository dealRepository;

	@Before
	public void deleteAllBeforeTests() throws Exception {
		dealRepository.deleteAll();
	}

	private String getCreateContent() {
		return "{"
				+ "\"createDate\": \"2018-12-31\","
				+ "\"endDate\": \"2018-12-31\","
				+ "\"publishDate\": \"2018-12-31\","
				+ "\"text\": \"teste\","
				+ "\"title\": \"teste\","
				+ "\"totalSold\": 0,"
				+ "\"type\": \"local\","
				+ "\"url\": \"url\""
				+ "}";
	}

	private String getUpdateContent() {
		return "{"
				+ "\"text\": \"testeText\","
				+ "\"title\": \"testeTitle\""
				+ "}";
	}

	@Test
	public void shouldCreateDeal() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/deal")
				.content(getCreateContent()))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.header().string("Location", Matchers.containsString("deal/")));
	}

	@Test
	public void shouldPartiallyUpdateEntity() throws Exception {

		final MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/v1/deal")
						.content(getCreateContent()))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

		final String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(MockMvcRequestBuilders.patch(location)
				.content(getUpdateContent()))
				.andExpect(MockMvcResultMatchers.status().isNoContent());

		mockMvc.perform(MockMvcRequestBuilders.get(location))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.text").value("testeText"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.type").value("local"));
	}

	@Test
	public void shouldReturnRepositoryIndex() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/deal"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$._links.profile").exists());
	}

	@Test
	public void shouldUpdateEntity() throws Exception {
		final MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/v1/deal")
						.content(getCreateContent()))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

		final String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(MockMvcRequestBuilders.put(location)
				.content(getUpdateContent()))
				.andExpect(MockMvcResultMatchers.status().isNoContent());

		mockMvc.perform(MockMvcRequestBuilders.get(location))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.text").value("testeText"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.type").doesNotExist());
	}
}
