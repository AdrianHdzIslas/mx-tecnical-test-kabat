package mx.kabat.bff_product.infraestructure.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import mx.kabat.bff_product.application.port.in.*;
import mx.kabat.bff_product.application.port.out.*;
import mx.kabat.bff_product.domain.model.*;
import mx.kabat.bff_product.infrastructure.controller.ProductController;
import mx.kabat.bff_product.infrastructure.controller.dto.CheckProductRequest;
import mx.kabat.bff_product.infrastructure.controller.dto.ProductRequest;
import mx.kabat.bff_product.infrastructure.controller.dto.SimpleProductRequest;
import mx.kabat.bff_product.infrastructure.controller.dto.UpdateStockRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private CreateProductPort createProductPort;

    @Mock
    private GetAllProductPort getAllProductPort;

    @Mock
    private FindProductPort findProductPort;

    @Mock
    private CheckAvailabilityPort checkAvailabilityPort;

    @Mock
    private UpdateStockProductPort updateStockProductPort;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private ProductModel sampleModel;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        objectMapper = new ObjectMapper();

        sampleModel = new ProductModel(1L, "Televisor LED", new BigDecimal("4999.99"), 10, true);
    }

    @Test
    void createProduct_ReturnsOkAndProductResponse() throws Exception {
        ProductRequest request = new ProductRequest(null, "Televisor LED", new BigDecimal("4999.99"), 10, true);
        when(createProductPort.createProduct(any(ProductModel.class))).thenReturn(sampleModel);

        mockMvc.perform(post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProduct").value(sampleModel.idProduct()))
                .andExpect(jsonPath("$.name").value("Televisor LED"))
                .andExpect(jsonPath("$.price").value(4999.99))
                .andExpect(jsonPath("$.stock").value(10))
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    void findAllProducts_ReturnsList() throws Exception {
        ProductModel model2 = new ProductModel(2L, "Laptop gamer", new BigDecimal("12499.50"), 5, true);
        when(getAllProductPort.findAllProducts()).thenReturn(List.of(sampleModel, model2));

        mockMvc.perform(get("/api/v1/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Televisor LED"))
                .andExpect(jsonPath("$[1].name").value("Laptop gamer"));
    }

    @Test
    void findProductById_ReturnsProduct() throws Exception {
        when(findProductPort.findProduct(1L)).thenReturn(sampleModel);

        mockMvc.perform(get("/api/v1/product/{idProduct}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProduct").value(1L))
                .andExpect(jsonPath("$.name").value("Televisor LED"));
    }

    @Test
    void checkAvailability_ReturnsMap() throws Exception {
        CheckProductRequest req = new CheckProductRequest();
        req.setProducts(List.of(1L, 2L));
        Map<Long, ProductInfoModel> map = new HashMap<>();
        map.put(1L, new ProductInfoModel(1L, new BigDecimal("4999.99"), 10, true));
        map.put(2L, new ProductInfoModel(2L, new BigDecimal("999.99"), 0, false));

        when(checkAvailabilityPort.checkAvailability(req.getProducts())).thenReturn(map);

        mockMvc.perform(post("/api/v1/product/check-availability")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.1.idProduct").value(1L))
                .andExpect(jsonPath("$.1.stock").value(10))
                .andExpect(jsonPath("$.2.active").value(false));
    }

    @Test
    void updateProduct_StockPatch_ReturnsNoContent() throws Exception {
        UpdateStockRequest updReq = new UpdateStockRequest();
        updReq.setProducts(List.of(
                new SimpleProductRequest(1L, -2),
                new SimpleProductRequest(2L, 3)
        ));

        doNothing().when(updateStockProductPort).updateStockProduct(any());

        mockMvc.perform(patch("/api/v1/product/stock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updReq)))
                .andExpect(status().isNoContent());

        ArgumentCaptor<List<SimpleProductInfo>> captor = ArgumentCaptor.forClass(List.class);
        verify(updateStockProductPort).updateStockProduct(captor.capture());
        List<SimpleProductInfo> infos = captor.getValue();
        assert infos.size() == 2;
        assert infos.get(0).idProduct().equals(1L);
        assert infos.get(0).amount() == -2;
    }
}
