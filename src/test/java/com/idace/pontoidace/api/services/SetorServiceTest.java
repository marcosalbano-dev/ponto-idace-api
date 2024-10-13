package com.idace.pontoidace.api.services;

import org.junit.Test;

import com.idace.pontoidace.api.entities.Setor;
import com.idace.pontoidace.api.repositories.SetorRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SetorServiceTest {

    @MockBean
    private SetorRepository setorRepository;

    @Autowired
    private SetorService setorService;

    private static final String nomeSetor = "UNITI";

    @Before
    public void setUp() throws Exception {
        BDDMockito.given(this.setorRepository.findByNomeSetor(Mockito.anyString())).willReturn(new Setor());
        BDDMockito.given(this.setorRepository.save(Mockito.any(Setor.class))).willReturn(new Setor());
    }

    @Test
    public void testBuscarSetorPorNome() {
        Optional<Setor> setor = this.setorService.buscarPorNomeSetor(nomeSetor);

        assertTrue(setor.isPresent());
    }

    @Test
    public void testPersistirSetor() {
        Setor setor = this.setorService.persistir(new Setor());

        assertNotNull(setor);
    }
}
