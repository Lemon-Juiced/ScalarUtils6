require 'json'

color_dyes = {
  "white" => "minecraft:white_dye",
  "light_gray" => "minecraft:light_gray_dye",
  "gray" => "minecraft:gray_dye",
  "black" => "minecraft:black_dye",
  "brown" => "minecraft:brown_dye",
  "red" => "minecraft:red_dye",
  "orange" => "minecraft:orange_dye",
  "yellow" => "minecraft:yellow_dye",
  "lime" => "minecraft:lime_dye",
  "green" => "minecraft:green_dye",
  "cyan" => "minecraft:cyan_dye",
  "light_blue" => "minecraft:light_blue_dye",
  "blue" => "minecraft:blue_dye",
  "purple" => "minecraft:purple_dye",
  "magenta" => "minecraft:magenta_dye",
  "pink" => "minecraft:pink_dye"
}

color_dyes.each do |color, dye|
  # 1. Crafting flat light from luminite_shard + dye
  recipe = {
    type: "minecraft:crafting_shapeless",
    ingredients: [
      { item: "scalarutils:luminite_shard" },
      { item: dye }
    ],
    result: {
      id: "scalarutils:#{color}_flat_light",
      count: 1
    }
  }
  File.write("#{color}_flat_light.json", JSON.pretty_generate(recipe))

  # 2. Convert flat light to inverted
  to_inverted = {
    type: "minecraft:crafting_shapeless",
    ingredients: [
      { item: "scalarutils:#{color}_flat_light" }
    ],
    result: {
      id: "scalarutils:inverted_#{color}_flat_light",
      count: 1
    }
  }
  File.write("to_inverted_#{color}_flat_light.json", JSON.pretty_generate(to_inverted))

  # 3. Convert inverted flat light to normal
  to_normal = {
    type: "minecraft:crafting_shapeless",
    ingredients: [
      { item: "scalarutils:inverted_#{color}_flat_light" }
    ],
    result: {
      id: "scalarutils:#{color}_flat_light",
      count: 1
    }
  }
  File.write("to_normal_#{color}_flat_light.json", JSON.pretty_generate(to_normal))
end