package org.catarina.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.catarina.entity.AddressBook;
import org.catarina.mapper.AddressBookMapper;
import org.catarina.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}
