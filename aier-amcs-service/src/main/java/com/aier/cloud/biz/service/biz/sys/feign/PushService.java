package com.aier.cloud.biz.service.biz.sys.feign;
/*
 * Copyright © 2004-2018 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 版权所有
 *
 * Licensed under the Aier EYE Hospital Group License;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.aierchina.com/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aier.cloud.basic.api.request.push.GoEasyMessage;


/**
 * interface服务
 *
 * @author xiaokek
 * @since 2019年12月27日 下午4:55:21
 */
@FeignClient(name = "aier-center-push")
public interface PushService {

	@RequestMapping(value = "/api/push/pushcenter/goPush", method = RequestMethod.POST)
	public boolean goPush(@RequestBody GoEasyMessage m);

}