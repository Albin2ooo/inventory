package in.stackroute.service;

import in.stackroute.dto.Item;
import in.stackroute.dto.ItemCodeRequestDto;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ItemserviceImpl implements ItemService {

    private RestTemplate restTemplate;

    //private final String itemServiceUrl = "http://item-service/api/v1/items";
    private final String itemServiceListItemsByCodesUrl = "http://localhost:8000/api/v1/items/search-codes";



    public ItemserviceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public List<Item> getItemsByIds(List<String> codes) {

        ItemCodeRequestDto dto = new ItemCodeRequestDto(codes);
        HttpEntity<ItemCodeRequestDto> request = new HttpEntity<>(dto);
        var response = restTemplate.postForEntity(itemServiceListItemsByCodesUrl,request,Item[].class);
        if(response.getStatusCode().is2xxSuccessful()){
            return Stream.of(response.getBody()).toList();
        }
        return List.of();




    }
}
