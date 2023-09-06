local userId = KEYS[1]
local inventoryId = KEYS[2]

local alreadyBuy = redis.call("get",inventoryId.."_"..userId)

if alreadyBuy then
    redis.log(redis.LOG_NOTICE, inventoryId.."_"..userId.." already exist")
    return "OWN"
end

local totalKey = "total"
local saleKey ="sale"
local total = redis.call("hget", inventoryId,totalKey)
local sale = redis.call("hget",inventoryId, saleKey)
if tonumber(sale) >= tonumber(total) then
    redis.log(redis.LOG_NOTICE, sale.." exceed "..total)
    return "MISS"
end

redis.call("set", inventoryId.."_"..userId,1)
local newSale = tonumber(sale+1)
redis.call("hset",inventoryId, saleKey, newSale)
return "GOT"

