package com.idace.pontoidace.api.repositories;

import static org.junit.Assert.assertEquals;

import com.idace.pontoidace.api.entities.Setor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SetorRepositoryTest {

    @Autowired
    private SetorRepository setorRepository;

    private static final String nomeSetor = "UNITI";

    @Before
    public void setUp() throws Exception {
        Setor setor = new Setor();
        setor.setNomeSetor("Setor de exemplo");
        this.setorRepository.save(setor);
    }

    @After
    public final void tearDown() {
        this.setorRepository.deleteAll();
    }

    @Test
    public void testBuscarPorNomeSetor() {
        Setor setor = this.setorRepository.findByNomeSetor(nomeSetor);

        assertEquals(nomeSetor, setor.getNomeSetor());
    }


}
